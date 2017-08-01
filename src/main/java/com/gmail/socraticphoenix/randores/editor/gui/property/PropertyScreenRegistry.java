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
package com.gmail.socraticphoenix.randores.editor.gui.property;

import com.gmail.socraticphoenix.collect.coupling.Pair;
import com.gmail.socraticphoenix.randores.editor.gui.ChildScreen;
import com.gmail.socraticphoenix.randores.editor.gui.ProjectScreen;
import com.gmail.socraticphoenix.randores.editor.model.property.FlammablePropertyModel;
import com.gmail.socraticphoenix.randores.editor.model.property.PropertyModel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class PropertyScreenRegistry {
    private static Map<String, RegistryItem> registry = new LinkedHashMap<>();

    public static void registerDefaults() {
        register("flammable", (s, m) -> new FlammablePropertyScreen(m, s), () -> new FlammablePropertyModel(1));
    }

    public static Set<String> keys() {
        return registry.keySet();
    }

    public static <T extends PropertyModel> void register(String name, BiFunction<ProjectScreen, T, ChildScreen> editor, Supplier<T> def) {
        registry.put(name, new RegistryItem(editor, def));
    }

    public static Pair<PropertyModel, ChildScreen> openDefault(String name, ProjectScreen screen) {
        RegistryItem item = registry.get(name);
        PropertyModel model = item.getDef().get();
        return Pair.of(model, item.getEditor().apply(screen, model));
    }

    public static ChildScreen openEditor(ProjectScreen screen, PropertyModel model) {
        return registry.get(model.id()).getEditor().apply(screen, model);
    }

    private static class RegistryItem {
        private BiFunction<ProjectScreen, PropertyModel, ChildScreen> editor;
        private Supplier<PropertyModel> def;

        public RegistryItem(BiFunction<ProjectScreen, ? extends PropertyModel, ChildScreen> editor, Supplier<? extends PropertyModel> def) {
            this.editor = (BiFunction<ProjectScreen, PropertyModel, ChildScreen>) editor;
            this.def = (Supplier<PropertyModel>) def;
        }

        public BiFunction<ProjectScreen, PropertyModel, ChildScreen> getEditor() {
            return this.editor;
        }

        public Supplier<PropertyModel> getDef() {
            return this.def;
        }

    }
}
