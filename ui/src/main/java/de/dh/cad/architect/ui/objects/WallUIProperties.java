/*******************************************************************************
 *     Architect - A free 2D/3D home and interior designer
 *     Copyright (C) 2021, 2022  Daniel Höh
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 *******************************************************************************/
package de.dh.cad.architect.ui.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import de.dh.cad.architect.model.ChangeSet;
import de.dh.cad.architect.model.coords.Length;
import de.dh.cad.architect.model.objects.BaseObject;
import de.dh.cad.architect.model.objects.Wall;
import de.dh.cad.architect.model.wallmodel.AdaptedModelAnchor;
import de.dh.cad.architect.model.wallmodel.WallAnchorPositions;
import de.dh.cad.architect.model.wallmodel.WallBevelType;
import de.dh.cad.architect.ui.Strings;
import de.dh.cad.architect.ui.controller.UiController;
import de.dh.cad.architect.ui.properties.ConstantUiProperty;
import de.dh.cad.architect.ui.properties.UiProperty;
import de.dh.cad.architect.ui.properties.UiProperty.PropertyType;
import de.dh.cad.architect.ui.view.ObjectReconcileOperation;
import de.dh.cad.architect.ui.view.construction.Abstract2DView;
import de.dh.cad.architect.ui.view.construction.ConstructionView;
import de.dh.cad.architect.ui.view.threed.Abstract3DView;

public class WallUIProperties extends BaseObjectUIRepresentation {
    public static final String KEY_PROPERTY_THICKNESS = "thickness";
    public static final String KEY_PROPERTY_BEVEL_TYPE_A = "beveltype-A";
    public static final String KEY_PROPERTY_BEVEL_TYPE_B = "beveltype-B";
    public static final String KEY_PROPERTY_WALL_HEIGHT_A = "wall-height-A";
    public static final String KEY_PROPERTY_WALL_HEIGHT_B = "wall-height-B";
    public static final String KEY_PROPERTY_WALL_LENGTH_BASE = "wall-length-base";
    public static final String KEY_PROPERTY_WALL_LENGTH_SIDE_1 = "wall-length-side-1";
    public static final String KEY_PROPERTY_WALL_LENGTH_SIDE_2 = "wall-length-side-2";

    public WallUIProperties() {
        super(new WallReconciler());
    }

    @Override
    public String getTypeName(Cardinality cardinality) {
        return cardinality == Cardinality.Singular ? Strings.OBJECT_TYPE_NAME_WALL_S : Strings.OBJECT_TYPE_NAME_WALL_P;
    }

    @Override
    protected void addProperties(Map<String, Collection<UiProperty<?>>> result, BaseObject bo, UiController uiController) {
        super.addProperties(result, bo, uiController);
        Wall wall = (Wall) bo;
        Collection<UiProperty<?>> properties = result.computeIfAbsent("Wand", cat -> new ArrayList<>());
        properties.addAll(Arrays.<UiProperty<?>>asList(
                new UiProperty<Length>(wall, KEY_PROPERTY_THICKNESS, "Wanddicke", PropertyType.Length, true) {
                    @Override
                    public Length getValue() {
                        return wall.getThickness();
                    }

                    @Override
                    public void setValue(Object value) {
                        Length thickness = (Length) value;
                        ChangeSet changeSet = new ChangeSet();
                        wall.setThickness(thickness);
                        changeSet.changed(wall);
                        WallReconciler.updateWallAnchors(wall, uiController, changeSet);
                        uiController.notifyChanges(changeSet);
                    }
                },
                new UiProperty<WallBevelType>(wall, KEY_PROPERTY_BEVEL_TYPE_A, "Wandverbinder A", PropertyType.WallBevelType, true) {
                    @Override
                    public WallBevelType getValue() {
                        return WallAnchorPositions.getWallBevelTypeOfAnchorDock(new AdaptedModelAnchor(wall.getAnchorWallHandleB())).orElse(null);
                    }

                    @Override
                    public void setValue(Object value) {
                        WallBevelType bevelType = (WallBevelType) value;
                        uiController.setWallBevelTypeOfAnchorDock(wall.getAnchorWallHandleA(), bevelType);
                    }
                },
                new UiProperty<WallBevelType>(wall, KEY_PROPERTY_BEVEL_TYPE_B, "Wandverbinder B", PropertyType.WallBevelType, true) {
                    @Override
                    public WallBevelType getValue() {
                        return WallAnchorPositions.getWallBevelTypeOfAnchorDock(new AdaptedModelAnchor(wall.getAnchorWallHandleB())).orElse(null);
                    }

                    @Override
                    public void setValue(Object value) {
                        WallBevelType bevelType = (WallBevelType) value;
                        uiController.setWallBevelTypeOfAnchorDock(wall.getAnchorWallHandleB(), bevelType);
                    }
                },
                new UiProperty<Length>(wall, KEY_PROPERTY_WALL_HEIGHT_A, "Höhe Seite A", PropertyType.Length, true) {
                    @Override
                    public Length getValue() {
                        return wall.getHeightA();
                    }

                    @Override
                    public void setValue(Object value) {
                        Length height = (Length) value;
                        wall.setHeightA(height);
                        ChangeSet changeSet = new ChangeSet();
                        changeSet.changed(wall);
                        ObjectReconcileOperation omo = new ObjectReconcileOperation("Anpassung von Wandankern an Höhenänderung Seite A");
                        omo.tryAddObjectToProcess(wall);
                        uiController.doReconcileObjects(omo, changeSet);
                        uiController.notifyChanges(changeSet);
                    }
                },
                new UiProperty<Length>(wall, KEY_PROPERTY_WALL_HEIGHT_B, "Höhe Seite B", PropertyType.Length, true) {
                    @Override
                    public Length getValue() {
                        return wall.getHeightB();
                    }

                    @Override
                    public void setValue(Object value) {
                        Length height = (Length) value;
                        wall.setHeightB(height);
                        ChangeSet changeSet = new ChangeSet();
                        changeSet.changed(wall);
                        ObjectReconcileOperation omo = new ObjectReconcileOperation("Anpassung von Wandankern an Höhenänderung Seite B");
                        omo.tryAddObjectToProcess(wall);
                        uiController.doReconcileObjects(omo, changeSet);
                        uiController.notifyChanges(changeSet);
                    }
                },
                new ConstantUiProperty<>(wall, KEY_PROPERTY_WALL_LENGTH_BASE, "Basis-Wandlänge", PropertyType.Length, wall.calculateBaseLength()),
                new ConstantUiProperty<>(wall, KEY_PROPERTY_WALL_LENGTH_SIDE_1, "Wandlänge Seite 1", PropertyType.Length, wall.calculateLengthSide1()),
                new ConstantUiProperty<>(wall, KEY_PROPERTY_WALL_LENGTH_SIDE_2, "Wandlänge Seite 2", PropertyType.Length, wall.calculateLengthSide2())
                ));
    }

    @Override
    public Abstract2DRepresentation create2DRepresentation(BaseObject modelObject, Abstract2DView parentView) {
        // Hack: Cast parent view to construction view should be removed when we have multiple 2D views
        return new WallConstructionRepresentation((Wall) modelObject, (ConstructionView) parentView);
    }

    @Override
    public Abstract3DRepresentation create3DRepresentation(BaseObject modelObject, Abstract3DView parentView) {
        return new Wall3DRepresentation((Wall) modelObject, parentView);
    }
}
