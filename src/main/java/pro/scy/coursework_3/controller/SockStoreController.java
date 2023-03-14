package pro.scy.coursework_3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.scy.coursework_3.controller.dto.ResponceDto;
import pro.scy.coursework_3.model.Color;
import pro.scy.coursework_3.model.Size;
import pro.scy.coursework_3.model.SocksBatch;
import pro.scy.coursework_3.service.SocksStoreService;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API для учета товара", description = "Приход, отпуск, брак, учёт")
@RequiredArgsConstructor
public class SockStoreController {

    private final SocksStoreService storeService;

    @PostMapping
    @Operation(summary = "Регестрирует приход товара на склад")
    @ApiResponse(responseCode = "200", description = "Операция успешна.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат.")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    public ResponseEntity<ResponceDto> accept(@RequestBody SocksBatch socksBatch) {
        storeService.accept(socksBatch);
        return ResponseEntity.ok(new ResponceDto("Успешно добавленно на склад"));
    }

    @PutMapping
    @Operation(summary = "Регестрирует приход товара со склада")
    @ApiResponse(responseCode = "200", description = "Операция успешна.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат.")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    public ResponseEntity<ResponceDto> issuance(@RequestBody SocksBatch socksBatch) {
        int socksCount = storeService.issuance(socksBatch);
        return ResponseEntity.ok(new ResponceDto(socksCount + "успешно отпущено на склада"));
    }

    @GetMapping
    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.")
    @ApiResponse(responseCode = "200", description = "Операция успешна.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат.")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    public ResponseEntity<ResponceDto> getCount(@RequestParam Color color,
                                         @RequestParam Size size,
                                         @RequestParam int cottonMin,
                                         @RequestParam int cottonMax) {
        int socksCount = storeService.getCount(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(new ResponceDto("Колличество товара на складе: " + socksCount));
    }

    @DeleteMapping
    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков")
    @ApiResponse(responseCode = "200", description = "Операция успешна.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат.")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    public ResponseEntity<ResponceDto> reject(@RequestBody SocksBatch socksBatch) {
        int socksCount = storeService.reject(socksBatch);
        return ResponseEntity.ok(new ResponceDto(socksCount + "успешно списанно со склада"));
    }
}
