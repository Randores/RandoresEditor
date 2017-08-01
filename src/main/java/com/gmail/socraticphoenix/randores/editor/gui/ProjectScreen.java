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
package com.gmail.socraticphoenix.randores.editor.gui;

import com.gmail.socraticphoenix.collect.IdentityList;
import com.gmail.socraticphoenix.jlsc.JLSCArray;
import com.gmail.socraticphoenix.jlsc.JLSCCompound;
import com.gmail.socraticphoenix.jlsc.JLSCConfiguration;
import com.gmail.socraticphoenix.jlsc.JLSCFormat;
import com.gmail.socraticphoenix.jlsc.skeleton.JLSCVerifier;
import com.gmail.socraticphoenix.jlsc.skeleton.JLSCVerifiers;
import com.gmail.socraticphoenix.jlsc.value.JLSCValue;
import com.gmail.socraticphoenix.parse.ParseResult;
import com.gmail.socraticphoenix.randores.editor.gui.ability.AbilityScreenRegistry;
import com.gmail.socraticphoenix.randores.editor.gui.property.PropertyScreenRegistry;
import com.gmail.socraticphoenix.randores.editor.model.CraftableModel;
import com.gmail.socraticphoenix.randores.editor.model.DefinitionModel;
import com.gmail.socraticphoenix.randores.editor.model.MaterialComponentModel;
import com.gmail.socraticphoenix.randores.editor.model.OreComponentModel;
import com.gmail.socraticphoenix.randores.editor.model.ability.AbilityModel;
import com.gmail.socraticphoenix.randores.editor.model.property.PropertyModel;
import com.gmail.socraticphoenix.randores.mod.component.MaterialType;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.gmail.socraticphoenix.randores.editor.gui.LinkedSpinnerModel.dualLinkOneMax;
import static com.gmail.socraticphoenix.randores.editor.gui.LinkedSpinnerModel.dualLinkZeroMax;

public class ProjectScreen {
    private JList definitions;
    private JTextField name;
    private JComboBox dimension;
    private JSpinner minDrops;
    private JSpinner maxDrops;
    private JSpinner minVein;
    private JSpinner maxVein;
    private JSpinner minY;
    private JSpinner maxY;
    private JSpinner minOccur;
    private JSpinner maxOccur;
    private JSpinner harvestLevel;
    private JTextField hardness;
    private JTextField resistance;
    private JCheckBox requiresSmeltingCheckBox;
    private JTextField smeltingXp;
    private JComboBox materialType;
    private JSpinner durability;
    private JSpinner enchantability;
    private JSpinner harvestLevelTool;
    private JSpinner helmetReduction;
    private JSpinner chestplateReduction;
    private JSpinner leggingsReduction;
    private JSpinner bootsReduction;
    private JTextField armorToughness;
    private JTextField efficiency;
    private JTextField damage;
    private JList craftables;
    private JList properties;
    private JList abilitiesMelee;
    private JList abilitiesProjectile;
    private JList abilitiesArmorPassive;
    private JList abilitiesArmorActive;
    private JButton addMelee;
    private JButton removeMelee;
    private JButton editMelee;
    private JButton addProperty;
    private JButton removeProperty;
    private JButton editProperty;
    private JButton addCraftable;
    private JButton removeCraftable;
    private JButton editCraftable;
    private JButton addProjectile;
    private JButton removeProjectile;
    private JButton editProjectile;
    private JButton addPassive;
    private JButton removePassive;
    private JButton editPassive;
    private JButton addActive;
    private JButton removeActive;
    private JButton editActive;
    private JButton addNewButton;
    private JButton removeButton;
    private JPanel colorExample;
    private JButton exportButton;
    private JButton loadButton;
    private JPanel rootPanel;

    private DefinitionModel currentModel;

    private List<JFrame> associatedWindows;

    private JColorChooser colorChooser;

    private JFileChooser fileChooser;

    public JList getProperties() {
        return this.properties;
    }


    public JList getAbilitiesProjectile() {
        return this.abilitiesProjectile;
    }

    public JList getAbilitiesArmorPassive() {
        return this.abilitiesArmorPassive;
    }

    public JList getAbilitiesArmorActive() {
        return this.abilitiesArmorActive;
    }

    public JList getAbilitiesMelee() {
        return this.abilitiesMelee;
    }

    public Optional<DefinitionModel> getModel() {
        return Optional.ofNullable(this.currentModel);
    }

    public void refreshAbilities() {
        this.abilitiesMelee.repaint();
        this.abilitiesProjectile.repaint();
        this.abilitiesArmorActive.repaint();
        this.abilitiesArmorPassive.repaint();
    }

    public void refreshProperties() {
        this.properties.repaint();
    }

    public void refreshCraftables() {
        this.craftables.repaint();
    }

    public List<JFrame> getAssociatedWindows() {
        return this.associatedWindows;
    }

    public void initGui() {
        this.associatedWindows = new IdentityList<>();

        this.harvestLevel.setModel(zeroMax(15));
        dualLinkOneMax(this.minDrops, this.maxDrops);
        dualLinkOneMax(this.minVein, this.maxVein);
        dualLinkZeroMax(this.minY, this.maxY);
        dualLinkZeroMax(this.minOccur, this.maxOccur);
        this.durability.setModel(oneUp());
        this.enchantability.setModel(zeroUp());
        this.harvestLevelTool.setModel(zeroUp());
        this.helmetReduction.setModel(zeroUp());
        this.chestplateReduction.setModel(zeroUp());
        this.leggingsReduction.setModel(zeroUp());
        this.bootsReduction.setModel(zeroUp());

        this.setEditor(this.minDrops, this.maxDrops,
                this.minVein, this.maxVein,
                this.minY, this.maxY,
                this.minOccur, this.maxOccur,
                this.durability, this.enchantability,
                this.harvestLevel, this.harvestLevelTool,
                this.helmetReduction, this.chestplateReduction,
                this.leggingsReduction, this.bootsReduction);

        this.floatListener(this.smeltingXp);
        this.floatListener(this.hardness);
        this.floatListener(this.resistance);
        this.floatListener(this.armorToughness);
        this.floatListener(this.efficiency);
        this.floatListener(this.damage);

        for (com.gmail.socraticphoenix.randores.mod.component.Dimension dimension : com.gmail.socraticphoenix.randores.mod.component.Dimension.values()) {
            this.dimension.addItem(dimension.name());
        }

        for (MaterialType type : MaterialType.values()) {
            this.materialType.addItem(type.name());
        }

        this.colorChooser = new JColorChooser();
        this.colorChooser.getSelectionModel().addChangeListener(e -> {
            Color c = this.colorChooser.getColor();
            this.colorExample.setForeground(c);
            this.colorExample.setBackground(c);
        });

        this.colorExample.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("Set Color");
                frame.add(colorChooser);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setSize(700, 400);
                frame.setVisible(true);
                associatedWindows.add(frame);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        associatedWindows.remove(frame);
                    }
                });
            }
        });

        this.updateSpinner(this.harvestLevel, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().setHarvestLevel(i));
        this.updateSpinner(this.minDrops, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().setMinDrops(i));
        this.updateSpinner(this.maxDrops, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().setMaxDrops(i));
        this.updateSpinner(this.minVein, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().setMinVein(i));
        this.updateSpinner(this.maxVein, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().setMaxVein(i));
        this.updateSpinner(this.minY, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().setMinY(i));
        this.updateSpinner(this.maxY, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().setMaxY(i));
        this.updateSpinner(this.minOccur, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().setMinOccurrences(i));
        this.updateSpinner(this.maxOccur, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().setMaxOccurrences(i));
        this.updateSpinner(this.durability, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().getMaterial().setMaxUses(i));
        this.updateSpinner(this.enchantability, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().getMaterial().setEnchantability(i));
        this.updateSpinner(this.harvestLevelTool, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().getMaterial().setHarvestLevel(i));
        this.updateSpinner(this.helmetReduction, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().getMaterial().getArmorReduction()[0] = i);
        this.updateSpinner(this.chestplateReduction, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().getMaterial().getArmorReduction()[1] = i);
        this.updateSpinner(this.leggingsReduction, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().getMaterial().getArmorReduction()[2] = i);
        this.updateSpinner(this.bootsReduction, s -> (int) s.getModel().getValue(), (d, i) -> d.getOre().getMaterial().getArmorReduction()[3] = i);

        this.updateText(this.smeltingXp, ProjectScreen::parseFloat, (d, f) -> d.getOre().setSmeltingXp(f));
        this.updateText(this.hardness, ProjectScreen::parseFloat, (d, f) -> d.getOre().setHardness(f));
        this.updateText(this.resistance, ProjectScreen::parseFloat, (d, f) -> d.getOre().setResistance(f));
        this.updateText(this.armorToughness, ProjectScreen::parseFloat, (d, f) -> d.getOre().getMaterial().setToughness(f));
        this.updateText(this.efficiency, ProjectScreen::parseFloat, (d, f) -> d.getOre().getMaterial().setEfficiency(f));
        this.updateText(this.damage, ProjectScreen::parseFloat, (d, f) -> d.getOre().getMaterial().setDamage(f));

        this.updateCombo(this.dimension, v -> com.gmail.socraticphoenix.randores.mod.component.Dimension.valueOf(String.valueOf(v.getSelectedItem())), (d, k) -> d.getOre().setDimension(k));
        this.updateCombo(this.materialType, v -> MaterialType.valueOf(String.valueOf(v.getSelectedItem())), (d, t) -> d.getOre().getMaterial().setType(t));

        this.updateColor(this.colorChooser, JColorChooser::getColor, DefinitionModel::setColor);

        this.updateText(this.name, JTextComponent::getText, (d, n) -> {
            d.setName(n);
            this.definitions.repaint();
        });

        this.definitions.setModel(new DefaultListModel());
        this.craftables.setModel(new DefaultListModel());
        this.properties.setModel(new DefaultListModel());
        this.abilitiesArmorPassive.setModel(new DefaultListModel());
        this.abilitiesArmorActive.setModel(new DefaultListModel());
        this.abilitiesProjectile.setModel(new DefaultListModel());
        this.abilitiesMelee.setModel(new DefaultListModel());

        this.addNewButton.addActionListener(e -> {
            this.associatedWindows.forEach(JFrame::dispose);
            this.associatedWindows.clear();
            DefinitionModel def = DefinitionModel.getDefault();
            ((DefaultListModel) this.definitions.getModel()).addElement(def);
            this.definitions.setSelectedValue(def, true);
        });
        this.removeButton.addActionListener(e -> {
            if (this.getModel().isPresent()) {
                this.associatedWindows.forEach(JFrame::dispose);
                this.associatedWindows.clear();
                DefinitionModel model = this.getModel().get();
                DefaultListModel listModel = (DefaultListModel) this.definitions.getModel();
                int ind = listModel.indexOf(model);
                listModel.removeElement(this.getModel().orElse(null));
                if (this.definitions.getModel().getSize() > 0) {
                    int nInd = ind == 0 ? 0 : ind - 1;
                    this.definitions.setSelectedIndex(nInd);
                }
            }
        });
        this.definitions.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                this.associatedWindows.forEach(JFrame::dispose);
                this.associatedWindows.clear();
                this.currentModel = (DefinitionModel) this.definitions.getSelectedValue();
                if (this.currentModel != null) {
                    this.setAllTo((DefinitionModel) this.definitions.getSelectedValue());
                }
            }
        });

        this.addProperty.addActionListener(e -> {
            JFrame frame = new SelectPropertyScreen(this).initAndShow();
            this.associatedWindows.add(frame);
        });

        this.removeProperty.addActionListener(e -> {
            PropertyModel propertyModel = (PropertyModel) this.properties.getSelectedValue();
            if (propertyModel != null) {
                ((DefaultListModel) this.properties.getModel()).removeElement(propertyModel);
            }
        });

        this.editProperty.addActionListener(e -> {
            PropertyModel propertyModel = (PropertyModel) this.properties.getSelectedValue();
            if (propertyModel != null) {
                ChildScreen screen = PropertyScreenRegistry.openEditor(this, propertyModel);
                JFrame frame = screen.initAndShow();
                this.associatedWindows.add(frame);
            }
        });


        ProjectScreen screen = this;
        JList[] lists = new JList[]{screen.getAbilitiesMelee(), screen.getAbilitiesProjectile(), screen.getAbilitiesArmorPassive(), screen.getAbilitiesArmorActive()};
        JButton[][] actionButtons = new JButton[][]{
                {addMelee, editMelee, removeMelee},
                {addProjectile, editProjectile, removeProjectile},
                {addPassive, editPassive, removePassive},
                {addActive, editActive, removeActive}
        };

        for (int i = 0; i < actionButtons.length; i++) {
            JButton[] buttons = actionButtons[i];
            JList list = lists[i];
            int flag = i;

            buttons[0].addActionListener(e -> {
                SelectAbilityScreen selectScreen = new SelectAbilityScreen(this, flag);
                JFrame frame = selectScreen.initAndShow();
                this.associatedWindows.add(frame);
            });

            buttons[1].addActionListener(e -> {
                AbilityModel abilityModel = (AbilityModel) list.getSelectedValue();
                if (abilityModel != null) {
                    ChildScreen childScreen = AbilityScreenRegistry.openEditor(this, abilityModel);
                    JFrame frame = childScreen.initAndShow();
                    this.associatedWindows.add(frame);
                }
            });

            buttons[2].addActionListener(e -> {
                if (list.getSelectedValue() != null) {
                    ((DefaultListModel) list.getModel()).removeElement(list.getSelectedValue());
                }
            });
        }

        this.addCraftable.addActionListener(e -> {
            SelectCraftableScreen selecctScreen = new SelectCraftableScreen(this);
            JFrame frame = selecctScreen.initAndShow();
            this.associatedWindows.add(frame);
        });

        this.editCraftable.addActionListener(e -> {
            CraftableModel model = (CraftableModel) this.craftables.getSelectedValue();
            if (model != null) {
                EditCraftableScreen editScreen = new EditCraftableScreen(model, this);
                JFrame frame = editScreen.initAndShow();
                this.associatedWindows.add(frame);
            }
        });

        this.removeCraftable.addActionListener(e -> {
            CraftableModel model = (CraftableModel) this.craftables.getSelectedValue();
            if (model != null) {
                ((DefaultListModel) this.craftables.getModel()).removeElement(model);
            }
        });

        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".jlsc") || f.getName().endsWith(".cjlsc");
            }

            @Override
            public String getDescription() {
                return "JLSC/CJLSC Files";
            }

        });
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.fileChooser.setMultiSelectionEnabled(false);

        JLSCVerifier verifier = JLSCVerifiers.skeleton()
                .require("definitions", JLSCVerifiers.array(JLSCVerifiers.type(DefinitionModel.class)))
                .build();

        this.loadButton.addActionListener(e -> {
            int op = this.fileChooser.showOpenDialog(null);
            if (op == JFileChooser.APPROVE_OPTION) {
                new Thread(() -> {
                    try {
                        File target = this.fileChooser.getSelectedFile();
                        JLSCConfiguration configuration;
                        if (target.getName().endsWith(".jlsc")) {
                            configuration = JLSCConfiguration.fromText(target, true);
                        } else if (target.getName().endsWith(".cjlsc")) {
                            configuration = JLSCConfiguration.fromCompressed(target, true);
                        } else {
                            throw new IllegalStateException("Unknown file extension for file " + target.getName());
                        }

                        ParseResult result = verifier.verify(JLSCValue.of(configuration.getCompound()));
                        if (result.isSuccesful()) {
                            EventQueue.invokeLater(() -> {
                                DefaultListModel listModel = (DefaultListModel) this.definitions.getModel();
                                listModel.removeAllElements();
                                JLSCArray array = configuration.getArray("definitions").get();
                                DefinitionModel first = null;
                                for (JLSCValue value : array) {
                                    DefinitionModel model = value.getAs(DefinitionModel.class).get();
                                    if (first == null) {
                                        first = model;
                                    }

                                    listModel.addElement(model);
                                }
                                this.definitions.setSelectedValue(first, true);
                            });
                        } else {
                            JOptionPane.showMessageDialog(null, result.buildMessage(), "Invalid Configuration", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Throwable err) {
                        JOptionPane.showMessageDialog(null, err.getMessage(), "Error Loading File", JOptionPane.ERROR_MESSAGE);
                    }
                }).start();
            }
        });

        this.exportButton.addActionListener(e -> {
            int op = this.fileChooser.showSaveDialog(null);
            if (op == JFileChooser.APPROVE_OPTION) {
                File target = this.fileChooser.getSelectedFile();
                JLSCArray definitions = new JLSCArray();
                DefaultListModel listModel = (DefaultListModel) this.definitions.getModel();
                for (int i = 0; i < listModel.getSize(); i++) {
                    definitions.add(listModel.getElementAt(i));
                }
                new Thread(() -> {
                    try {
                        JLSCConfiguration configuration = new JLSCConfiguration(new JLSCCompound().toConcurrent(), target, target.getName().endsWith(".cjlsc") ? JLSCFormat.COMPRESSED_BYTES : JLSCFormat.TEXT, true);
                        configuration.put("definitions", definitions);
                        configuration.save();
                    } catch (Throwable err) {
                        JOptionPane.showMessageDialog(null, err.getMessage(), "Error Saving File", JOptionPane.ERROR_MESSAGE);
                    }
                }).start();
            }
        });
    }

    private void setListData(JList listData, Object... data) {
        DefaultListModel model = (DefaultListModel) listData.getModel();
        model.removeAllElements();
        for (Object ele : data) {
            model.addElement(ele);
        }
    }

    private void setAllTo(DefinitionModel model) {
        this.name.setText(model.getName());
        this.colorExample.setBackground(model.getColor());
        this.colorExample.setForeground(model.getColor());

        this.setListData(craftables, model.getCraftables().toArray());
        this.setListData(properties, model.getPropertiesList().toArray());
        this.setListData(abilitiesMelee, model.getAbilitySeries().getMelee().toArray());
        this.setListData(abilitiesProjectile, model.getAbilitySeries().getProjectile().toArray());
        this.setListData(abilitiesArmorPassive, model.getAbilitySeries().getArmorPassive().toArray());
        this.setListData(abilitiesArmorActive, model.getAbilitySeries().getArmorActive().toArray());

        OreComponentModel ore = model.getOre();
        this.dimension.setSelectedItem(ore.getDimension().name());
        this.reset(minDrops, maxDrops, ore.getMinDrops(), ore.getMaxDrops());
        this.reset(minVein, maxVein, ore.getMinVein(), ore.getMaxVein());
        this.reset(minY, maxY, ore.getMinY(), ore.getMaxY());
        this.reset(minOccur, maxOccur, ore.getMinOccurrences(), ore.getMaxOccurrences());
        this.harvestLevel.setValue(ore.getHarvestLevel());
        this.requiresSmeltingCheckBox.setSelected(ore.isRequiresSmelting());
        this.reset(smeltingXp, ore.getSmeltingXp());
        this.reset(hardness, ore.getHardness());
        this.reset(resistance, ore.getResistance());

        MaterialComponentModel material = ore.getMaterial();
        this.materialType.setSelectedItem(material.getType().name());
        this.durability.setValue(material.getMaxUses());
        this.enchantability.setValue(material.getEnchantability());
        this.harvestLevelTool.setValue(material.getHarvestLevel());
        JSpinner[] armor = {helmetReduction, chestplateReduction, leggingsReduction, bootsReduction};
        for (int i = 0; i < armor.length; i++) {
            armor[i].setValue(material.getArmorReduction()[i]);
        }
        this.reset(armorToughness, material.getToughness());
        this.reset(efficiency, material.getEfficiency());
        this.reset(damage, material.getDamage());
    }

    private void reset(JTextComponent component, Object val) {
        component.setText(String.valueOf(val));
    }

    private void reset(JSpinner min, JSpinner max, int nMin, int nMax) {
        LinkedSpinnerModel minModel = (LinkedSpinnerModel) min.getModel();
        LinkedSpinnerModel maxModel = (LinkedSpinnerModel) max.getModel();

        min.setValue(minModel.getStart());
        max.setValue(maxModel.getStart());

        max.setValue(nMax);
        min.setValue(nMin);
    }

    private static float parseFloat(JTextComponent component) {
        String txt = component.getText();
        if (txt.isEmpty()) {
            return 0;
        } else {
            return Float.parseFloat(txt);
        }
    }

    private <T extends JSpinner, K> void updateSpinner(T val, Function<T, K> getter, BiConsumer<DefinitionModel, K> setter) {
        UpdatingChangeListener<T, K> listener = new UpdatingChangeListener<>(val, getter, setter, this);
        val.addChangeListener(listener);
    }

    private <T extends JComboBox, K> void updateCombo(T val, Function<T, K> getter, BiConsumer<DefinitionModel, K> setter) {
        UpdatingChangeListener<T, K> listener = new UpdatingChangeListener<>(val, getter, setter, this);
        val.addActionListener(listener);
    }


    private <T extends JColorChooser, K> void updateColor(T val, Function<T, K> getter, BiConsumer<DefinitionModel, K> setter) {
        UpdatingChangeListener<T, K> listener = new UpdatingChangeListener<>(val, getter, setter, this);
        val.getSelectionModel().addChangeListener(listener);
    }

    private <T extends JTextComponent, K> void updateText(T val, Function<T, K> getter, BiConsumer<DefinitionModel, K> setter) {
        UpdatingChangeListener<T, K> listener = new UpdatingChangeListener<>(val, getter, setter, this);
        val.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listener.stateChanged(null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listener.stateChanged(null);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                listener.stateChanged(null);
            }
        });
    }

    private void floatListener(JTextComponent component) {
        FloatKeyListener listener = new FloatKeyListener(component);
        component.addKeyListener(listener);
    }

    private void setEditor(JSpinner... spinners) {
        for (JSpinner spinner : spinners) {
            spinner.setEditor(this.editor(spinner));
        }
    }

    private DefaultEditor editor(JSpinner spinner) {
        return new SpinnerModelEditor(spinner);
    }

    private SpinnerNumberModel zeroMax(int max) {
        return new SpinnerNumberModel(0, 0, max, 1);
    }

    private SpinnerNumberModel zeroUp() {
        return new SpinnerNumberModel(0, 0, null, 1);
    }

    private SpinnerNumberModel oneUp() {
        return new SpinnerNumberModel(1, 1, null, 1);
    }

    public JFrame initDisplay() {
        initGui();
        JFrame frame = new JFrame("Randores Editor");
        frame.setSize(1000, 500);
        frame.add(this.rootPanel);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int op = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (op == JOptionPane.OK_OPTION) {
                    frame.dispose();
                }
            }
        });
        return frame;
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    public JList getCraftables() {
        return craftables;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        rootPanel.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(panel1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        name = new JTextField();
        name.setToolTipText("Base name");
        panel2.add(name, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Name:");
        panel2.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Color:");
        panel2.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Ore Properties:");
        panel2.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Material Properties:");
        panel2.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(10, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        dimension = new JComboBox();
        dimension.setToolTipText("Generation dimension");
        panel3.add(dimension, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Dimension:");
        panel3.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Drops:");
        panel3.add(label6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minDrops = new JSpinner();
        minDrops.setToolTipText("Minimum drops");
        panel3.add(minDrops, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxDrops = new JSpinner();
        maxDrops.setToolTipText("Maximum drops");
        panel3.add(maxDrops, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Vein Size:");
        panel3.add(label7, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minVein = new JSpinner();
        minVein.setToolTipText("Minium vein size");
        panel3.add(minVein, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxVein = new JSpinner();
        maxVein.setToolTipText("Maximum vein size");
        panel3.add(maxVein, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Spawn Height:");
        panel3.add(label8, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minY = new JSpinner();
        minY.setToolTipText("Minimum spawn height");
        panel3.add(minY, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxY = new JSpinner();
        maxY.setToolTipText("Maximum spawn height");
        panel3.add(maxY, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Occurrences:");
        panel3.add(label9, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minOccur = new JSpinner();
        minOccur.setToolTipText("Minimum occurrences");
        panel3.add(minOccur, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxOccur = new JSpinner();
        maxOccur.setToolTipText("Maximum occurrences");
        panel3.add(maxOccur, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Harvest Level:");
        panel3.add(label10, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        harvestLevel = new JSpinner();
        harvestLevel.setToolTipText("Ore harvest level");
        panel3.add(harvestLevel, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Smelting:");
        panel3.add(label11, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Hardness:");
        panel3.add(label12, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("Resistance:");
        panel3.add(label13, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        hardness = new JTextField();
        hardness.setToolTipText("Hardness");
        panel3.add(hardness, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        resistance = new JTextField();
        resistance.setToolTipText("Explosion resistance");
        panel3.add(resistance, new GridConstraints(9, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        requiresSmeltingCheckBox = new JCheckBox();
        requiresSmeltingCheckBox.setText("Requires Smelting");
        requiresSmeltingCheckBox.setToolTipText("Requires smelting");
        panel3.add(requiresSmeltingCheckBox, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        smeltingXp = new JTextField();
        smeltingXp.setToolTipText("Smelting experience");
        panel3.add(smeltingXp, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("Smelting XP:");
        panel3.add(label14, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(8, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JLabel label15 = new JLabel();
        label15.setText("Type:");
        panel4.add(label15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setText("Durability:");
        panel4.add(label16, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        durability = new JSpinner();
        durability.setToolTipText("Durability");
        panel4.add(durability, new GridConstraints(1, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setText("Enchantability:");
        panel4.add(label17, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enchantability = new JSpinner();
        enchantability.setToolTipText("Enchantability");
        panel4.add(enchantability, new GridConstraints(2, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        label18.setText("Harvest Level:");
        panel4.add(label18, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        harvestLevelTool = new JSpinner();
        harvestLevelTool.setToolTipText("Tool harvest level");
        panel4.add(harvestLevelTool, new GridConstraints(3, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setText("Armor Reduction:");
        panel4.add(label19, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        helmetReduction = new JSpinner();
        helmetReduction.setToolTipText("Helmet reduction");
        panel4.add(helmetReduction, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chestplateReduction = new JSpinner();
        chestplateReduction.setToolTipText("Chestplate reduction");
        panel4.add(chestplateReduction, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        leggingsReduction = new JSpinner();
        leggingsReduction.setToolTipText("Leggings reduction");
        panel4.add(leggingsReduction, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bootsReduction = new JSpinner();
        bootsReduction.setToolTipText("Boots reduction");
        panel4.add(bootsReduction, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        materialType = new JComboBox();
        materialType.setToolTipText("Material type");
        panel4.add(materialType, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("Efficiency:");
        panel4.add(label20, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("Damage:");
        panel4.add(label21, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("Armor Toughness:");
        panel4.add(label22, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        armorToughness = new JTextField();
        armorToughness.setToolTipText("Armor toughness");
        panel4.add(armorToughness, new GridConstraints(5, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        efficiency = new JTextField();
        efficiency.setToolTipText("Efficiency");
        panel4.add(efficiency, new GridConstraints(6, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        damage = new JTextField();
        damage.setToolTipText("Base damage");
        panel4.add(damage, new GridConstraints(7, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        colorExample = new JPanel();
        colorExample.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        colorExample.setBackground(new Color(-16777216));
        colorExample.setToolTipText("Set color");
        panel2.add(colorExample, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        Font label23Font = this.$$$getFont$$$(null, -1, 14, label23.getFont());
        if (label23Font != null) label23.setFont(label23Font);
        label23.setText("Current Definition Properties:");
        panel2.add(label23, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel5.add(scrollPane2, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        definitions = new JList();
        definitions.setSelectionMode(0);
        definitions.setToolTipText("Material definitions");
        scrollPane2.setViewportView(definitions);
        addNewButton = new JButton();
        addNewButton.setText("Add New");
        addNewButton.setToolTipText("Add new definition");
        panel5.add(addNewButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeButton = new JButton();
        removeButton.setText("Remove");
        removeButton.setToolTipText("Remove selected definition");
        panel5.add(removeButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        Font label24Font = this.$$$getFont$$$(null, -1, 14, label24.getFont());
        if (label24Font != null) label24.setFont(label24Font);
        label24.setText("Material Definitions:");
        panel5.add(label24, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel6, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel6.add(panel7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(panel8, new GridConstraints(1, 2, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel8.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JScrollPane scrollPane3 = new JScrollPane();
        panel8.add(scrollPane3, new GridConstraints(1, 0, 2, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        abilitiesProjectile = new JList();
        abilitiesProjectile.setSelectionMode(0);
        abilitiesProjectile.setToolTipText("Projectile abilities");
        scrollPane3.setViewportView(abilitiesProjectile);
        final JLabel label25 = new JLabel();
        label25.setText("Projectile Abilities:");
        panel8.add(label25, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel8.add(panel9, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addProjectile = new JButton();
        addProjectile.setText("Add");
        addProjectile.setToolTipText("Add projectile ability");
        panel9.add(addProjectile, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeProjectile = new JButton();
        removeProjectile.setText("Remove");
        removeProjectile.setToolTipText("Remove selected projectile ability");
        panel9.add(removeProjectile, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editProjectile = new JButton();
        editProjectile.setText("Edit");
        editProjectile.setToolTipText("Edit selected projectile ability");
        panel9.add(editProjectile, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(panel10, new GridConstraints(0, 0, 5, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel10.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JScrollPane scrollPane4 = new JScrollPane();
        panel10.add(scrollPane4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        craftables = new JList();
        craftables.setSelectionMode(0);
        craftables.setToolTipText("Craftable components");
        scrollPane4.setViewportView(craftables);
        final JLabel label26 = new JLabel();
        label26.setText("Craftables:");
        panel10.add(label26, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel10.add(panel11, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addCraftable = new JButton();
        addCraftable.setText("Add");
        addCraftable.setToolTipText("Add craftable component");
        panel11.add(addCraftable, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeCraftable = new JButton();
        removeCraftable.setText("Remove");
        removeCraftable.setToolTipText("Remove selected component");
        panel11.add(removeCraftable, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editCraftable = new JButton();
        editCraftable.setText("Edit");
        editCraftable.setToolTipText("Edit selected component");
        panel11.add(editCraftable, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(panel12, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel12.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JScrollPane scrollPane5 = new JScrollPane();
        panel12.add(scrollPane5, new GridConstraints(1, 0, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        abilitiesMelee = new JList();
        abilitiesMelee.setSelectionMode(0);
        abilitiesMelee.setToolTipText("Melee abilities");
        scrollPane5.setViewportView(abilitiesMelee);
        final JLabel label27 = new JLabel();
        label27.setText("Melee Abilities:");
        panel12.add(label27, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel12.add(panel13, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addMelee = new JButton();
        addMelee.setText("Add");
        addMelee.setToolTipText("Add melee ability");
        panel13.add(addMelee, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeMelee = new JButton();
        removeMelee.setText("Remove");
        removeMelee.setToolTipText("Remove selected melee ability");
        panel13.add(removeMelee, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editMelee = new JButton();
        editMelee.setText("Edit");
        editMelee.setToolTipText("Edit selected melee ability");
        panel13.add(editMelee, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel6.add(panel14, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel14.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel14.add(panel15, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel15.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JScrollPane scrollPane6 = new JScrollPane();
        panel15.add(scrollPane6, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        abilitiesArmorPassive = new JList();
        abilitiesArmorPassive.setSelectionMode(0);
        abilitiesArmorPassive.setToolTipText("Passive armor abilities");
        scrollPane6.setViewportView(abilitiesArmorPassive);
        final JLabel label28 = new JLabel();
        label28.setText("Passive Armor:");
        panel15.add(label28, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel15.add(panel16, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addPassive = new JButton();
        addPassive.setText("Add");
        addPassive.setToolTipText("Add passive armor ability");
        panel16.add(addPassive, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removePassive = new JButton();
        removePassive.setText("Remove");
        removePassive.setToolTipText("Remove selected passive armor ability");
        panel16.add(removePassive, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editPassive = new JButton();
        editPassive.setText("Edit");
        editPassive.setToolTipText("Edit selected passive armor ability");
        panel16.add(editPassive, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel14.add(panel17, new GridConstraints(0, 0, 3, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel17.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JScrollPane scrollPane7 = new JScrollPane();
        panel17.add(scrollPane7, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        properties = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        properties.setModel(defaultListModel1);
        properties.setSelectionMode(0);
        properties.setToolTipText("Properties");
        scrollPane7.setViewportView(properties);
        final JLabel label29 = new JLabel();
        label29.setText("Properties:");
        panel17.add(label29, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel17.add(panel18, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addProperty = new JButton();
        addProperty.setText("Add");
        addProperty.setToolTipText("Add property");
        panel18.add(addProperty, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeProperty = new JButton();
        removeProperty.setText("Remove");
        removeProperty.setToolTipText("Remove selected property");
        panel18.add(removeProperty, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editProperty = new JButton();
        editProperty.setText("Edit");
        editProperty.setToolTipText("Edit selected property");
        panel18.add(editProperty, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel19 = new JPanel();
        panel19.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel14.add(panel19, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel19.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JScrollPane scrollPane8 = new JScrollPane();
        panel19.add(scrollPane8, new GridConstraints(1, 0, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        abilitiesArmorActive = new JList();
        abilitiesArmorActive.setSelectionMode(0);
        abilitiesArmorActive.setToolTipText("Active armor abilities");
        scrollPane8.setViewportView(abilitiesArmorActive);
        final JLabel label30 = new JLabel();
        label30.setText("Active Armor:");
        panel19.add(label30, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel20 = new JPanel();
        panel20.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel19.add(panel20, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addActive = new JButton();
        addActive.setText("Add");
        addActive.setToolTipText("Add active armor ability");
        panel20.add(addActive, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeActive = new JButton();
        removeActive.setText("Remove");
        removeActive.setToolTipText("Remove selected active armor ability");
        panel20.add(removeActive, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editActive = new JButton();
        editActive.setText("Edit");
        editActive.setToolTipText("Edit selected active armor ability");
        panel20.add(editActive, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label31 = new JLabel();
        label31.setEnabled(true);
        Font label31Font = this.$$$getFont$$$(null, -1, 14, label31.getFont());
        if (label31Font != null) label31.setFont(label31Font);
        label31.setText("Current Definition Properties:");
        panel6.add(label31, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel21 = new JPanel();
        panel21.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel21, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        exportButton = new JButton();
        exportButton.setText("Export");
        exportButton.setToolTipText("Export to file");
        panel21.add(exportButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadButton = new JButton();
        loadButton.setText("Load");
        loadButton.setToolTipText("Load from file");
        panel21.add(loadButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
