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
package com.gmail.socraticphoenix.randores.editor.gui.ability;

import com.gmail.socraticphoenix.collect.coupling.Pair;
import com.gmail.socraticphoenix.randores.editor.gui.ChildScreen;
import com.gmail.socraticphoenix.randores.editor.gui.ProjectScreen;
import com.gmail.socraticphoenix.randores.editor.model.ability.AbilityModel;
import com.gmail.socraticphoenix.randores.editor.model.ability.PotionEffectModel;
import com.gmail.socraticphoenix.randores.editor.model.ability.PotionModel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class AbilityScreenRegistry {
    private static Map<String, RegistryItem> registry = new LinkedHashMap<>();

    public static void registerDefaults() {
        register("potionEffect", (s, m) -> new PotionEffectAbilityScreen(m, s), () -> new PotionEffectModel(new PotionModel(PotionModel.KNOWN_POTIONS.get(0))));
    }

    public static <T extends AbilityModel> void register(String name,BiFunction<ProjectScreen, T, ChildScreen> editor, Supplier<T> def) {
        registry.put(name, new RegistryItem(editor, def));
    }

    public static Set<String> keys() {
        return registry.keySet();
    }

    public static Pair<AbilityModel, ChildScreen> openDefault(String name, ProjectScreen screen) {
        RegistryItem item = registry.get(name);
        AbilityModel model = item.getDef().get();
        return Pair.of(model, item.getEditor().apply(screen, model));
    }

    public static ChildScreen openEditor(ProjectScreen screen, AbilityModel model) {
        return registry.get(model.id()).getEditor().apply(screen, model);
    }

    private static class RegistryItem {
        private BiFunction<ProjectScreen, AbilityModel, ChildScreen> editor;
        private Supplier<AbilityModel> def;

        public RegistryItem(BiFunction<ProjectScreen, ? extends AbilityModel, ChildScreen> editor, Supplier<? extends AbilityModel> def) {
            this.editor = (BiFunction<ProjectScreen, AbilityModel, ChildScreen>) editor;
            this.def = (Supplier<AbilityModel>) def;
        }

        public BiFunction<ProjectScreen, AbilityModel, ChildScreen> getEditor() {
            return this.editor;
        }

        public Supplier<AbilityModel> getDef() {
            return this.def;
        }

    }

}
