package pl.jkan.ecommerce.delivery.infrastructure;

import pl.jkan.ecommerce.delivery.domain.DeliveryMechanism;
import pl.jkan.ecommerce.delivery.domain.DeliverySubject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DumpToFileDeliveryMechanism implements DeliveryMechanism {
    @Override
    public void handleDelivery(DeliverySubject deliverySubject) {
        try {
            String path = "/tmp/delivery.log";
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
            fileWriter.append(getContent(deliverySubject));
            bufferFileWriter.close();
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }

    private String getContent(DeliverySubject deliverySubject) {
        return String.format("Order id: %s \n", deliverySubject.getOrderId());
    }
}
