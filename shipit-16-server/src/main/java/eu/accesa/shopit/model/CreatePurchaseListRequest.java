package eu.accesa.shopit.model;

import java.util.List;

public class CreatePurchaseListRequest {

    private List<CreatePurchaseRequest> purchaseList;

    public List<CreatePurchaseRequest> getPurchaseList() {
        return purchaseList;
    }
}
