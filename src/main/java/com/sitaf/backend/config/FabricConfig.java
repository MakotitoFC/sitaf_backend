package com.sitaf.backend.config;

import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.identity.Identities;
import org.hyperledger.fabric.client.identity.Identity;
import org.hyperledger.fabric.client.identity.Signer;
import org.hyperledger.fabric.client.identity.Signers;
import org.hyperledger.fabric.client.identity.X509Identity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.cert.CertificateException;

import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.TlsChannelCredentials;

@Configuration
public class FabricConfig {

    @Value("${fabric.mspId:Org1MSP}")
    private String mspId;

    @Value("${fabric.channelName:mychannel}")
    private String channelName;

    @Value("${fabric.chaincodeName:medicamentos}")
    private String chaincodeName;

    @Value("${fabric.peerEndpoint:localhost:7051}")
    private String peerEndpoint;

    @Value("${fabric.peerOverride:peer0.org1.example.com}")
    private String peerOverride;

    @Value("${fabric.tlsCertPath:}")
    private String tlsCertPath;

    @Value("${fabric.certPath:}")
    private String certPath;

    @Value("${fabric.keyPath:}")
    private String keyPath;

    @Bean
    public Gateway gateway() throws Exception {
        if (certPath == null || certPath.isEmpty()) {
            System.out.println("No Fabric credentials provided. Fabric connection will fail if invoked.");
            return null; // El usuario deberá poner los certificados reales
        }

        Path tlsCert = Paths.get(tlsCertPath);
        Path cert = Paths.get(certPath);
        Path key = Paths.get(keyPath);

        ManagedChannel channel = Grpc.newChannelBuilder(peerEndpoint, TlsChannelCredentials.newBuilder()
                .trustManager(tlsCert.toFile())
                .build())
                .overrideAuthority(peerOverride)
                .build();

        return Gateway.newInstance()
                .identity(newIdentity(cert))
                .signer(newSigner(key))
                .connection(channel)
                .evaluateOptions(options -> options.withDeadlineAfter(5, java.util.concurrent.TimeUnit.SECONDS))
                .endorseOptions(options -> options.withDeadlineAfter(15, java.util.concurrent.TimeUnit.SECONDS))
                .submitOptions(options -> options.withDeadlineAfter(5, java.util.concurrent.TimeUnit.SECONDS))
                .commitStatusOptions(options -> options.withDeadlineAfter(1, java.util.concurrent.TimeUnit.MINUTES))
                .connect();
    }

    private Identity newIdentity(Path certPath) throws IOException, CertificateException {
        var certReader = Files.newBufferedReader(certPath);
        var certificate = Identities.readX509Certificate(certReader);
        return new X509Identity(mspId, certificate);
    }

    private Signer newSigner(Path keyPath) throws IOException, InvalidKeyException {
        var keyReader = Files.newBufferedReader(keyPath);
        var privateKey = Identities.readPrivateKey(keyReader);
        return Signers.newPrivateKeySigner(privateKey);
    }
}
