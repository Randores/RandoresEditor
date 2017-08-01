/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 socraticphoenix@gmail.com
 * Copyright (c) 2017 contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.gmail.socraticphoenix.randores.editor.model.ability;

import com.gmail.socraticphoenix.jlsc.serialization.annotation.Name;
import com.gmail.socraticphoenix.jlsc.serialization.annotation.Serializable;
import com.gmail.socraticphoenix.jlsc.serialization.annotation.SerializationConstructor;
import com.gmail.socraticphoenix.jlsc.serialization.annotation.Serialize;

import java.util.List;

@Serializable
public class AbilitySeriesModel {
    @Serialize(value = "armorPassive", reflect = false)
    private List<AbilityModel> armorPassive;
    @Serialize(value = "armorActive", reflect = false)
    private List<AbilityModel> armorActive;
    @Serialize(value = "projectile", reflect = false)
    private List<AbilityModel> projectile;
    @Serialize(value = "melee", reflect = false)
    private List<AbilityModel> melee;

    @SerializationConstructor
    public AbilitySeriesModel(@Name("armorPassive") List<AbilityModel> armorPassive, @Name("armorActive") List<AbilityModel> armorActive, @Name("melee") List<AbilityModel> melee, @Name("projectile") List<AbilityModel> projectile) {
        this.armorActive = armorActive;
        this.armorPassive = armorPassive;
        this.projectile = projectile;
        this.melee = melee;
    }

    public List<AbilityModel> getArmorPassive() {
        return this.armorPassive;
    }

    public AbilitySeriesModel setArmorPassive(List<AbilityModel> armorPassive) {
        this.armorPassive = armorPassive;
        return this;
    }

    public List<AbilityModel> getArmorActive() {
        return this.armorActive;
    }

    public AbilitySeriesModel setArmorActive(List<AbilityModel> armorActive) {
        this.armorActive = armorActive;
        return this;
    }

    public List<AbilityModel> getProjectile() {
        return this.projectile;
    }

    public AbilitySeriesModel setProjectile(List<AbilityModel> projectile) {
        this.projectile = projectile;
        return this;
    }

    public List<AbilityModel> getMelee() {
        return this.melee;
    }

    public AbilitySeriesModel setMelee(List<AbilityModel> melee) {
        this.melee = melee;
        return this;
    }

}