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
package com.gmail.socraticphoenix.randores.editor.model;

import com.gmail.socraticphoenix.jlsc.serialization.annotation.Name;
import com.gmail.socraticphoenix.jlsc.serialization.annotation.Serializable;
import com.gmail.socraticphoenix.jlsc.serialization.annotation.SerializationConstructor;
import com.gmail.socraticphoenix.jlsc.serialization.annotation.Serialize;

@Serializable
public class MaterialComponentModel implements ComponentModel {
    @Serialize(value = "type", reflect = false)
    private MaterialTypeModel type;
    @Serialize(value = "armorReduction", reflect = false, comments = "Armor reduction values for helmet, chestplate, leggings, and boots, in that order")
    private int[] armorReduction;
    @Serialize(value = "harvestLevel", reflect = false, comments = "The harvest level tools made from this material work on")
    private int harvestLevel;
    @Serialize(value = "durability", reflect = false)
    private int maxUses;
    @Serialize(value = "efficiency", reflect = false)
    private float efficiency;
    @Serialize(value = "damage", reflect = false)
    private float damage;
    @Serialize(value = "toughness", reflect = false)
    private float toughness;
    @Serialize(value = "enchantability", reflect = false)
    private int enchantability;

    @SerializationConstructor
    public MaterialComponentModel(@Name("type") MaterialTypeModel type, @Name("armorReduction") int[] armorReduction, @Name("harvestLevel") int harvestLevel, @Name("durability") int maxUses, @Name("efficiency") float efficiency, @Name("damage") float damage, @Name("toughness") float toughness, @Name("enchantability") int enchantability) {
        this.type = type;
        this.armorReduction = armorReduction;
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.damage = damage;
        this.toughness = toughness;
        this.enchantability = enchantability;
    }

    public MaterialTypeModel getType() {
        return this.type;
    }

    public MaterialComponentModel setType(MaterialTypeModel type) {
        this.type = type;
        return this;
    }

    public int[] getArmorReduction() {
        return this.armorReduction;
    }

    public MaterialComponentModel setArmorReduction(int[] armorReduction) {
        this.armorReduction = armorReduction;
        return this;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public MaterialComponentModel setHarvestLevel(int harvestLevel) {
        this.harvestLevel = harvestLevel;
        return this;
    }

    public int getMaxUses() {
        return this.maxUses;
    }

    public MaterialComponentModel setMaxUses(int maxUses) {
        this.maxUses = maxUses;
        return this;
    }

    public float getEfficiency() {
        return this.efficiency;
    }

    public MaterialComponentModel setEfficiency(float efficiency) {
        this.efficiency = efficiency;
        return this;
    }

    public float getDamage() {
        return this.damage;
    }

    public MaterialComponentModel setDamage(float damage) {
        this.damage = damage;
        return this;
    }

    public float getToughness() {
        return this.toughness;
    }

    public MaterialComponentModel setToughness(float toughness) {
        this.toughness = toughness;
        return this;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public MaterialComponentModel setEnchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    @Override
    public String write() {
        return "material[type=" + this.type.getValue() + "]";
    }

    public String toString() {
        return write();
    }

}