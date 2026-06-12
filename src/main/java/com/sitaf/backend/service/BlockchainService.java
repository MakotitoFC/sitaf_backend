package com.sitaf.backend.service;

import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class BlockchainService {

    @Autowired(required = false)
    private Gateway gateway;

    @Value("${fabric.channelName:mychannel}")
    private String channelName;

    @Value("${fabric.chaincodeName:medicamentos}")
    private String chaincodeName;

    private Contract getContract() {
        if (gateway == null) {
            throw new RuntimeException("Gateway is not initialized. Please configure Fabric certificates.");
        }
        Network network = gateway.getNetwork(channelName);
        return network.getContract(chaincodeName);
    }

    public String consultarHistorial(String lote) throws Exception {
        byte[] result = getContract().evaluateTransaction("consultarHistorial", lote);
        return new String(result, StandardCharsets.UTF_8);
    }

    public void marcarLoteDefectuoso(String lote) throws Exception {
        getContract().submitTransaction("marcarLoteDefectuoso", lote);
    }

    public String rastrearPacientesAfectados(String lote) throws Exception {
        byte[] result = getContract().evaluateTransaction("rastrearPacientesAfectados", lote);
        return new String(result, StandardCharsets.UTF_8);
    }
}
