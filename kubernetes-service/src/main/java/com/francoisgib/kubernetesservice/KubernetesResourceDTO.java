package com.francoisgib.kubernetesservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KubernetesResourceDTO {
    private String uid;
    private String name;
}
