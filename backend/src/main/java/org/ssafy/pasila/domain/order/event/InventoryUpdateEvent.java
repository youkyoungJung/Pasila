package org.ssafy.pasila.domain.order.event;

import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class InventoryUpdateEvent {

    private String productOptionName;
    private int remainingQuantity;

}
