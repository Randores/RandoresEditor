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
import com.gmail.socraticphoenix.randores.editor.model.ability.AbilitySeriesModel;
import com.gmail.socraticphoenix.randores.editor.model.property.PropertyModel;
import com.gmail.socraticphoenix.randores.mod.component.Dimension;
import com.gmail.socraticphoenix.randores.mod.component.MaterialType;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

@Serializable
public class DefinitionModel implements WritableModel {
    @Serialize(value = "color", reflect = false)
    private Color color;
    @Serialize(value = "name", reflect = false)
    private String name;


    @Serialize(value = "properties", reflect = false)
    private List<PropertyModel> propertiesList;


    @Serialize(value = "abilities", reflect = false)
    private AbilitySeriesModel abilitySeries;

    @Serialize(value = "ore", reflect = false)
    private OreComponentModel ore;
    @Serialize(value = "components", reflect = false)
    private List<CraftableModel> craftables;

    @SerializationConstructor
    public DefinitionModel(@Name("color") Color color, @Name("name") String name, @Name("ore") OreComponentModel ore, @Name("components") List<CraftableModel> craftables, @Name("properties") List<PropertyModel> properties, @Name("abilities") AbilitySeriesModel series) {
        this.abilitySeries = series;
        this.color = color;
        this.ore = ore;
        this.craftables = craftables;
        this.name = name;
        this.propertiesList = properties;
    }

    public static DefinitionModel getDefault() {
        return new DefinitionModel(Color.BLACK, "",
                new OreComponentModel(
                        new MaterialComponentModel(MaterialType.INGOT, new int[]{0, 0, 0, 0}, 1, 100, 1f, 1f, 0f, 0)
                        , Dimension.OVERWORLD, 1, 1, 1, 1, 255, 0, 1, 1, true, 0f, 3f, 3f, 1)
                , new ArrayList<>(), new ArrayList<>(), new AbilitySeriesModel(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    public Color getColor() {
        return this.color;
    }

    public DefinitionModel setColor(Color color) {
        this.color = color;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public DefinitionModel setName(String name) {
        this.name = name;
        return this;
    }

    public List<PropertyModel> getPropertiesList() {
        return this.propertiesList;
    }

    public DefinitionModel setPropertiesList(List<PropertyModel> propertiesList) {
        this.propertiesList = propertiesList;
        return this;
    }

    public AbilitySeriesModel getAbilitySeries() {
        return this.abilitySeries;
    }

    public DefinitionModel setAbilitySeries(AbilitySeriesModel abilitySeries) {
        this.abilitySeries = abilitySeries;
        return this;
    }

    public OreComponentModel getOre() {
        return this.ore;
    }

    public DefinitionModel setOre(OreComponentModel ore) {
        this.ore = ore;
        return this;
    }

    public List<CraftableModel> getCraftables() {
        return this.craftables;
    }

    public DefinitionModel setCraftables(List<CraftableModel> craftables) {
        this.craftables = craftables;
        return this;
    }

    @Override
    public String write() {
        return "definition[name=" + this.name + "]";
    }

    public String toString() {
        return write();
    }

}
