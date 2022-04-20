package presentationLayer;

import bussinessLayer.DeliveryService;

public interface Observer {

    void update(DeliveryService a);
}
