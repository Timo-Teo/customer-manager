package com.camuendo.timoteo.customermanager.core.purchase_orders;

import com.camuendo.timoteo.customermanager.core.clients.ClientsServiceImpl;
import com.camuendo.timoteo.customermanager.core.clients.entities.Client;
import com.camuendo.timoteo.customermanager.core.items.ItemsServiceImpl;
import com.camuendo.timoteo.customermanager.core.items.dto.ResponseItemDTO;
import com.camuendo.timoteo.customermanager.core.items.entities.Item;
import com.camuendo.timoteo.customermanager.core.purchase_orders.dto.CreatePurchaseOrderDTO;
import com.camuendo.timoteo.customermanager.core.purchase_orders.dto.ResponsePurchaseOrderDTO;
import com.camuendo.timoteo.customermanager.core.purchase_orders.dto.UpdatePurchaseOrderDTO;
import com.camuendo.timoteo.customermanager.core.purchase_orders.entity.PurchaseOrder;
import com.camuendo.timoteo.customermanager.core.purchase_orders.enums.OrderStatus;
import com.camuendo.timoteo.customermanager.core.purchase_orders.repository.PurchaseOrderRepository;
import com.camuendo.timoteo.customermanager.core.purchase_orders.util.ItemQuantityUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ItemsServiceImpl itemsService;
    private final ClientsServiceImpl clientsService;

    public PurchaseOrderServiceImpl(
            PurchaseOrderRepository purchaseOrderRepository,
            ItemsServiceImpl itemsService,
            ClientsServiceImpl clientsService
    ) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.itemsService = itemsService;
        this.clientsService = clientsService;
    }

    private String createUniqueCode() {
        Long lastId = purchaseOrderRepository.lastId();
        if (lastId == null) {
            lastId = 0L;
        }
        String formattedId = String.format("%06d", lastId + 1);
        return "OC-" + formattedId;
    }

    private ResponsePurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder) {
        ResponsePurchaseOrderDTO purchaseOrderDTO = new ResponsePurchaseOrderDTO();
        purchaseOrderDTO.setId(purchaseOrder.getId());
        purchaseOrderDTO.setClient(purchaseOrder.getClient());
        purchaseOrderDTO.setStatus(purchaseOrder.getStatus().toString());
        purchaseOrderDTO.setDate(purchaseOrder.getDate());
        purchaseOrderDTO.setUniqueCode(purchaseOrder.getUniqueCode());


        List<ResponseItemDTO> items = new ArrayList<>();
        for (Item item : purchaseOrder.getItems()) {
            ResponseItemDTO itemDTO = new ResponseItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setName(item.getName());
            itemDTO.setPrice(item.getPrice());
            items.add(itemDTO);
        }

        purchaseOrderDTO.setItems(items);
        return purchaseOrderDTO;
    }


    private float calculateTotal(List<ResponseItemDTO> items) {
        return items.stream()
                .map(ResponseItemDTO::getPrice)
                .reduce(0f, Float::sum);
    }

    private List<Item> getItems(List<ItemQuantityUtil> itemsDTO) {
        List<Item> items = new ArrayList<>();

        itemsDTO.forEach(i -> {
            Item item = itemsService.findById(i.getItemId());

            for (int j = 0; j < i.getQuantity(); j++) {
                items.add(item);
                this.itemsService.verifyStock(item.getId(), j + 1);
            }
        });

        return items;
    }

    @Override
    public ResponsePurchaseOrderDTO save(CreatePurchaseOrderDTO createPurchaseOrderDTO) {
        Client client = clientsService.findById(createPurchaseOrderDTO.getClientId());

        List<Item> items = getItems(createPurchaseOrderDTO.getItems());

        LocalDateTime date = LocalDateTime.now();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setStatus(OrderStatus.IN_PROGRESS);
        purchaseOrder.setUniqueCode(createUniqueCode());
        purchaseOrder.setDate(date);
        purchaseOrder.setItems(items);
        purchaseOrder.setClient(client);

        purchaseOrderRepository.save(purchaseOrder);

        ResponsePurchaseOrderDTO purchaseOrderDTO = convertToDTO(purchaseOrder);
        float total = calculateTotal(purchaseOrderDTO.getItems());
        purchaseOrderDTO.setTotal(total);

        return purchaseOrderDTO;
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder findById(Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        if (purchaseOrder.isEmpty()) {
            throw new EntityNotFoundException("Purchase order not found");
        }
        return purchaseOrder.get();
    }

    @Override
    public ResponsePurchaseOrderDTO findByUniqueCode(String uniqueCode) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findPurchaseOrderByUniqueCode(uniqueCode);
        if (purchaseOrder.isEmpty()) {
            throw new EntityNotFoundException("Purchase order not found");
        }

        ResponsePurchaseOrderDTO purchaseOrderDTO = convertToDTO(purchaseOrder.get());

        float total = calculateTotal(purchaseOrderDTO.getItems());
        purchaseOrderDTO.setTotal(total);

        return purchaseOrderDTO;
    }

    @Override
    public List<PurchaseOrder> findAllByClientId(Long clientId) {
        Client client = clientsService.findById(clientId);
        return purchaseOrderRepository.findAllByClientId(client.getId());
    }

    @Override
    public PurchaseOrder update(Long id, UpdatePurchaseOrderDTO updatePurchaseOrderDTO) {
        PurchaseOrder purchaseOrder = findById(id);

        List<Item> items = getItems(updatePurchaseOrderDTO.getItems());
        purchaseOrder.setItems(items);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public void delete(Long id) {
        PurchaseOrder purchaseOrder = findById(id);
        purchaseOrder.setStatus(OrderStatus.CANCELLED);
        purchaseOrderRepository.save(purchaseOrder);
    }
}
