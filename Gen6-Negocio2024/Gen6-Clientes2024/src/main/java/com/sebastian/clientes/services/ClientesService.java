package com.sebastian.clientes.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.sebastian.clientes.models.Cliente;
import com.sebastian.clientes.repositories.ClienteRepository;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Component
public class ClientesService {
    @Autowired
    ClienteRepository clienteRepo;

    @Autowired
    @Qualifier("clienteWebFlux")
    private WebClient.Builder webClient;

    HttpClient client = HttpClient.create()
       //Connection Timeout: is a period within which a connection between a client and a server must be established
       .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
       .option(ChannelOption.SO_KEEPALIVE, true)
       .option(EpollChannelOption.TCP_KEEPIDLE, 300)
       .option(EpollChannelOption.TCP_KEEPINTVL, 60)
       //Response Timeout: The maximun time we wait to receive a response after sending a request
       .responseTimeout(Duration.ofSeconds(1))
       // Read and Write Timeout: A read timeout occurs when no data was read within a certain
       //period of time, while the write timeout when a write operation cannot finish at a specific time
       .doOnConnected(connection -> {
           connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
           connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
    });




    public List<Cliente> getAll() {
        List<Cliente> listado = new ArrayList<>();
        listado  = this.clienteRepo.findAll();
        return listado;
    }
    
    public Optional<Cliente> findById(Long id ) {
        Optional<Cliente> find = this.clienteRepo.findById(id);
        if(find.isPresent()) {
            return find;
        } else {
            return Optional.empty();
        }
    }

    public Cliente save(Cliente t) {
        return this.clienteRepo.save(t);
    }

    public void deleteById(Cliente t) {
        this.clienteRepo.delete(t);
    }

    public List<Cliente> getAllWithInfo() {
        List<Cliente> listado = new ArrayList<>();
        this.clienteRepo.findAll().forEach(x ->{
            x.getProducto().forEach(y ->{
                try {
                    y.setProductName(this.getProductInfo(y.getProductoId()));
                } catch (UnknownHostException e) {
                    // TODO: handle exception
                    throw new RuntimeException(e);
                }
            });
            listado.add(x);
        });
        return listado;
    }

    // TODO: Método que consume el microservicio de productos.
    private String getProductInfo(Long id ) throws UnknownError, UnknownHostException {
        String name = "";
        try {
            WebClient build = webClient.clientConnector(new ReactorClientHttpConnector(client))
            .baseUrl("http://localhost:8084/productos")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
            JsonNode block = build.method(HttpMethod.GET).uri("/" + id)
            .retrieve().bodyToMono(JsonNode.class).block();

            name = block.get("name").asText();
        } catch(WebClientResponseException ex) {
            if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                return "";
            } else {
                throw new UnknownHostException(ex.getMessage());
            }
        }
        return name;

    }



}
