package com.camuendo.timoteo.customermanager.core.purchase_orders.dto;

import com.camuendo.timoteo.customermanager.core.purchase_orders.util.ItemQuantityUtil;
import lombok.Data;

import java.util.List;

@Data
public class UpdatePurchaseOrderDTO {

    private List<ItemQuantityUtil> items;

}
