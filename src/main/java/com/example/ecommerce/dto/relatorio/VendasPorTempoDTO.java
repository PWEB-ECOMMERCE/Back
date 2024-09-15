package com.example.ecommerce.dto.relatorio;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public record VendasPorTempoDTO(LocalDate data, int valorTotal) {
}
