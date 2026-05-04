package com.kotlin.rent_flow.controllers

import com.kotlin.rent_flow.dtos.request.CreateBuildingRequest
import com.kotlin.rent_flow.dtos.request.UpdateBuildingRequest
import com.kotlin.rent_flow.dtos.response.BuildingDetailResponse
import com.kotlin.rent_flow.dtos.response.BuildingResponse
import com.kotlin.rent_flow.services.BuildingServices
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/buildings")
class BuildingController(
    private val buildingService: BuildingServices
) {
    @PostMapping
    fun create(
        @Valid @RequestBody request: CreateBuildingRequest
    ): BuildingResponse {
        return buildingService.create(request)
    }

    @GetMapping
    fun getAll(): List<BuildingResponse> {
        return buildingService.getall()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): BuildingDetailResponse {
        return buildingService.getById(id)
    }
//
//    @PutMapping("/{id}")
//    fun update(
//        @PathVariable id: UUID,
//        @RequestBody request: UpdateBuildingRequest
//    ): BuildingResponse {
//        return buildingService.update(id, request)
//    }
//
//    @DeleteMapping("/{id}")
//    fun delete(@PathVariable id: UUID) {
//        buildingService.delete(id)
//    }
}