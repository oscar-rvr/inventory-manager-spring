package com.grid.inventorymanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Entity
@Table(name = "computers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "asset_id")
public class Computer extends Asset {
    private Integer ram;
    private Integer disk;
    private String core;

    @Column(name = "screen_state")
    private String screenState;

    @Column(name = "keyboard_state")
    private String keyboardState;

    @Column(name = "shell_state")
    private String shellState;

    private String comments;
}
