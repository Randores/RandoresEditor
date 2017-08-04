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
import com.gmail.socraticphoenix.randores.component.Dimension;

@Serializable
public class OreComponentModel implements ComponentModel {
    @Serialize(value = "material", reflect = false)
    private MaterialComponentModel material;
    @Serialize(value = "dimension", reflect = false)
    private Dimension dimension;

    @Serialize(value = "minDrops", reflect = false)
    private int minDrops;
    @Serialize(value = "maxDrops", reflect = false)
    private int maxDrops;

    @Serialize(value = "minVein", reflect = false)
    private int minVein;
    @Serialize(value = "maxVein", reflect = false)
    private int maxVein;

    @Serialize(value = "minY", reflect = false)
    private int minY;
    @Serialize(value = "maxY", reflect = false)
    private int maxY;

    @Serialize(value = "minOccurrences", reflect = false)
    private int minOccurrences;
    @Serialize(value = "maxOccurrences", reflect = false)
    private int maxOccurrences;

    @Serialize(value = "smeltingXp", reflect = false)
    private float smeltingXp;
    @Serialize(value = "requiresSmelting", reflect = false)
    private boolean requiresSmelting;

    @Serialize(value = "hardness", reflect = false)
    private float hardness;
    @Serialize(value = "resistance", reflect = false)
    private float resistance;

    @Serialize(value = "harvestLevel", reflect = false)
    private int harvestLevel;

    @SerializationConstructor
    public OreComponentModel(@Name("material") MaterialComponentModel material, @Name("dimension") Dimension dimension, @Name("maxDrops") int maxDrops, @Name("minDrops") int minDrops, @Name("maxVein") int maxVein, @Name("minVein") int minVein, @Name("maxY") int maxY, @Name("minY") int minY, @Name("minOccurrences") int minOccurrences, @Name("maxOccurrences") int maxOccurrences, @Name("requiresSmelting") boolean requiresSmelting, @Name("smeltingXp") float smeltingXp, @Name("hardness") float hardness, @Name("resistance") float resistance, @Name("harvestLevel") int harvestLevel) {
        this.material = material;
        this.dimension = dimension;
        this.maxDrops = maxDrops;
        this.minDrops = minDrops;
        this.maxVein = maxVein;
        this.minVein = minVein;
        this.maxY = maxY;
        this.minY = minY;
        this.minOccurrences = minOccurrences;
        this.maxOccurrences = maxOccurrences;
        this.requiresSmelting = requiresSmelting;
        this.smeltingXp = smeltingXp;
        this.hardness = hardness;
        this.resistance = resistance;
        this.harvestLevel = harvestLevel;
    }

    public MaterialComponentModel getMaterial() {
        return this.material;
    }

    public OreComponentModel setMaterial(MaterialComponentModel material) {
        this.material = material;
        return this;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public OreComponentModel setDimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public int getMinDrops() {
        return this.minDrops;
    }

    public OreComponentModel setMinDrops(int minDrops) {
        this.minDrops = minDrops;
        return this;
    }

    public int getMaxDrops() {
        return this.maxDrops;
    }

    public OreComponentModel setMaxDrops(int maxDrops) {
        this.maxDrops = maxDrops;
        return this;
    }

    public int getMinVein() {
        return this.minVein;
    }

    public OreComponentModel setMinVein(int minVein) {
        this.minVein = minVein;
        return this;
    }

    public int getMaxVein() {
        return this.maxVein;
    }

    public OreComponentModel setMaxVein(int maxVein) {
        this.maxVein = maxVein;
        return this;
    }

    public int getMinY() {
        return this.minY;
    }

    public OreComponentModel setMinY(int minY) {
        this.minY = minY;
        return this;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public OreComponentModel setMaxY(int maxY) {
        this.maxY = maxY;
        return this;
    }

    public int getMinOccurrences() {
        return this.minOccurrences;
    }

    public OreComponentModel setMinOccurrences(int minOccurrences) {
        this.minOccurrences = minOccurrences;
        return this;
    }

    public int getMaxOccurrences() {
        return this.maxOccurrences;
    }

    public OreComponentModel setMaxOccurrences(int maxOccurrences) {
        this.maxOccurrences = maxOccurrences;
        return this;
    }

    public float getSmeltingXp() {
        return this.smeltingXp;
    }

    public OreComponentModel setSmeltingXp(float smeltingXp) {
        this.smeltingXp = smeltingXp;
        return this;
    }

    public boolean isRequiresSmelting() {
        return this.requiresSmelting;
    }

    public OreComponentModel setRequiresSmelting(boolean requiresSmelting) {
        this.requiresSmelting = requiresSmelting;
        return this;
    }

    public float getHardness() {
        return this.hardness;
    }

    public OreComponentModel setHardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    public float getResistance() {
        return this.resistance;
    }

    public OreComponentModel setResistance(float resistance) {
        this.resistance = resistance;
        return this;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public OreComponentModel setHarvestLevel(int harvestLevel) {
        this.harvestLevel = harvestLevel;
        return this;
    }

    @Override
    public String write() {
        return "ore[type=" + this.material.getType() + ", dimension=" + this.dimension + "]";
    }

    public String toString() {
        return write();
    }

}
