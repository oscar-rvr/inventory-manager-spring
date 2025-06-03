package com.grid.assets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "computers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
