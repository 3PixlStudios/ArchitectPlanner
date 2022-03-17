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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import de.dh.cad.architect.model.ChangeSet;
import de.dh.cad.architect.model.coords.Position2D;
import de.dh.cad.architect.model.coords.Position3D;
import de.dh.cad.architect.model.objects.Anchor;
import de.dh.cad.architect.model.objects.Ceiling;
import de.dh.cad.architect.ui.Constants;
import de.dh.cad.architect.ui.objects.IntermediatePoint.IntermediatePointCallback;
import de.dh.cad.architect.ui.utils.CoordinateUtils;
import de.dh.cad.architect.ui.view.construction.Abstract2DView;
import de.dh.cad.architect.ui.view.construction.ConstructionView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Scale;

public class CeilingConstructionRepresentation extends AbstractAnchoredObjectConstructionRepresentation implements IModificationFeatureProvider {
    public static enum AnchorDragMode {
        EdgeHandleAnchors() {
            @Override
            public boolean canDragAnchor(Anchor anchor) {
                return Ceiling.isEdgeHandleAnchor(anchor);
            }
        },
        CeilingHandleAnchors() {
            @Override
            public boolean canDragAnchor(Anchor anchor) {
                return Ceiling.isCeilingHandleAnchor(anchor);
            }
        },
        None() {
            @Override
            public boolean canDragAnchor(Anchor anchor) {
                return false;
            }
        };

        public abstract boolean canDragAnchor(Anchor anchor);
    }

    protected static final String HOOK_RESOURCE = "/de/dh/cad/architect/ui/objects/ceiling-hook.png";
    protected static final int HOOK_SIZE = Constants.TWO_D_INFO_SYMBOLS_SIZE;

    protected final Polygon mShape;
    protected final ImageView mHook1;
    protected final Scale mHookScaleCorrection1;
    protected final ImageView mHook2;
    protected final Scale mHookScaleCorrection2;
    protected final ImageView mHook3;
    protected final Scale mHookScaleCorrection3;
    protected List<IntermediatePoint> mIntermediatePoints = null;
    protected AnchorDragMode mAnchorDragMode = AnchorDragMode.None;

    public CeilingConstructionRepresentation(Ceiling ceiling, Abstract2DView parentView) {
        super(ceiling, parentView);
        mShape = new Polygon();
        setViewOrder(Constants.VIEW_ORDER_CEILING);
        addScaled(mShape);

        mHook1 = buildHook();
        mHook2 = buildHook();
        mHook3 = buildHook();
        mHookScaleCorrection1 = addUnscaled(mHook1);
        mHookScaleCorrection2 = addUnscaled(mHook2);
        mHookScaleCorrection3 = addUnscaled(mHook3);

        ChangeListener<Boolean> propertiesUpdaterListener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateProperties();
            }
        };
        selectedProperty().addListener(propertiesUpdaterListener);
        objectSpottedProperty().addListener(propertiesUpdaterListener);
        objectFocusedProperty().addListener(propertiesUpdaterListener);
        objectEmphasizedProperty().addListener(propertiesUpdaterListener);
    }

    protected ImageView buildHook() {
        Image image = new Image(getClass().getResourceAsStream(HOOK_RESOURCE));
        ImageView result = new ImageView(image);
        result.setFitWidth(HOOK_SIZE);
        result.setFitHeight(HOOK_SIZE);
        return result;
    }

    public Ceiling getCeiling() {
        return (Ceiling) mModelObject;
    }

    @Override
    public ConstructionView getParentView() {
        return (ConstructionView) mParentView;
    }

    protected void updateProperties() {
        mShape.setFill(Color.LIGHTGRAY);
        mShape.setStrokeType(StrokeType.INSIDE);
        boolean focused = isObjectFocused();
        mHook1.setVisible(focused);
        mHook2.setVisible(focused);
        mHook3.setVisible(focused);
        if (isSelected()) {
            mShape.setStroke(SELECTED_OBJECTS_COLOR);
        } else {
            mShape.setStroke(Color.BLACK);
        }
        if (isObjectFocused()) {
            setViewOrder(Constants.VIEW_ORDER_CEILING + Constants.VIEW_ORDER_OFFSET_FOCUSED);
        } else {
            setViewOrder(Constants.VIEW_ORDER_CEILING + Constants.VIEW_ORDER_OFFSET_NORMAL);
        }
        configureMainBorderDefault(mShape);
        if (isObjectEmphasized()) {
            mShape.setFill(null);
        }
    }

    protected void updatePoints() {
        List<Double> points = new ArrayList<>();
        Ceiling ceiling = getCeiling();
        for (Anchor anchor : ceiling.getEdgeHandleAnchors()) {
            Position2D position = anchor.requirePosition2D();
            points.add(CoordinateUtils.lengthToCoords(position.getX()));
            points.add(CoordinateUtils.lengthToCoords(position.getY()));
        }
        mShape.getPoints().setAll(points);

        Position3D posA = ceiling.getAnchorA().requirePosition3D();
        Position3D posB = ceiling.getAnchorB().requirePosition3D();
        Position3D posC = ceiling.getAnchorC().requirePosition3D();
        positionHook(mHook1, mHookScaleCorrection1, posA);
        positionHook(mHook2, mHookScaleCorrection2, posB);
        positionHook(mHook3, mHookScaleCorrection3, posC);
    }

    protected void positionHook(ImageView hook, Scale hookScaleCorrection, Position3D ceilingAnchorPos) {
        double x = CoordinateUtils.lengthToCoords(ceilingAnchorPos.getX());
        double y = CoordinateUtils.lengthToCoords(ceilingAnchorPos.getY());

        double scaleCompensation = getScaleCompensation();

        double centerDelta = 8 * Math.sqrt(scaleCompensation) // ... 8 pixels further but compensate anchor stroke thickness a bit, looks good
                        + HOOK_SIZE * 0.8 * scaleCompensation; // ... + fixed length for image
        hook.setX(x - centerDelta);
        hook.setY(y - centerDelta);
        hookScaleCorrection.setPivotX(x - centerDelta);
        hookScaleCorrection.setPivotY(y - centerDelta);
    }


    protected IntermediatePoint createIntermediatePoint() {
        IntermediatePoint result = new IntermediatePoint(getParentView(), new IntermediatePointCallback() {
            @Override
            protected void detachIntermediatePoint(IntermediatePoint source) {
                mIntermediatePoints.remove(source);
            }

            @Override
            protected Anchor createHandleAnchor(IntermediatePoint source, Anchor anchorBefore, Anchor anchorAfter, Position2D bendPosition) {
                Ceiling ceiling = getCeiling();
                ChangeSet changeSet = new ChangeSet();
                Anchor anchor = ceiling.createEdgeAnchor(anchorAfter, bendPosition, changeSet).getEdgeHandleAnchor();
                mParentView.getUiController().notifyChanges(changeSet);
                return anchor;
            }
        });
        result.updateScale(getParentView().getScaleCompensation());
        return result;
    }

    protected void addIntermediatePoints() {
        if (mIntermediatePoints != null) {
            // Intermediate points already added
            return;
        }
        mIntermediatePoints = new ArrayList<>();
        Iterator<Anchor> ia = getCeiling().getEdgeHandleAnchors().iterator();
        if (!ia.hasNext()) {
            return;
        }
        Anchor firstAnchor = ia.next();
        Anchor lastAnchor = firstAnchor;
        while (ia.hasNext() || firstAnchor != null) {
            Anchor currentAnchor;
            if (ia.hasNext()) {
                currentAnchor = ia.next();
            } else {
                // Position last intermediate point between last dock and first one
                currentAnchor = firstAnchor;
                firstAnchor = null;
            }
            IntermediatePoint ip = createIntermediatePoint();
            ip.update(lastAnchor, currentAnchor);
            mIntermediatePoints.add(ip);
            lastAnchor = currentAnchor;
        }
    }

    protected void updateIntermediatePoints() {
        if (mIntermediatePoints == null) {
            // No intermediate points visible ATM
            return;
        }
        Ceiling ceiling = getCeiling();
        List<Anchor> anchors = ceiling.getEdgeHandleAnchors();

        // Update amount of visible intermediate points
        while (anchors.size() < mIntermediatePoints.size()) {
            int lastIndex = mIntermediatePoints.size() - 1;
            IntermediatePoint lastIP = mIntermediatePoints.get(lastIndex);
            lastIP.dispose();
            mIntermediatePoints.remove(lastIndex);
        }
        while (anchors.size() > mIntermediatePoints.size()) {
            IntermediatePoint ip = createIntermediatePoint();
            mIntermediatePoints.add(ip);
        }
        Iterator<Anchor> ia = anchors.iterator();
        if (!ia.hasNext()) {
            return;
        }
        Anchor firstAnchor = ia.next();
        Anchor lastAnchor = firstAnchor;
        int ipIndex = 0;
        while (ia.hasNext() || firstAnchor != null) {
            Anchor currentDock;
            if (ia.hasNext()) {
                currentDock = ia.next();
            } else {
                // Position last intermediate point between last anchor and first anchor
                currentDock = firstAnchor;
                firstAnchor = null;
            }
            IntermediatePoint ip = mIntermediatePoints.get(ipIndex);
            ip.update(lastAnchor, currentDock);
            lastAnchor = currentDock;
            ipIndex++;
        }
        boolean visible = !ceiling.isHidden();
        for (IntermediatePoint ip : mIntermediatePoints) {
            ip.setVisible(visible);
        }
    }

    protected void removeIntermediatePoints() {
        if (mIntermediatePoints == null) {
            // No intermediate points visible ATM
            return;
        }
        for (IntermediatePoint ip : mIntermediatePoints) {
            ip.dispose();
        }
        mIntermediatePoints = null;
    }

    @Override
    public void enableModificationFeatures() {
        mAnchorDragMode = AnchorDragMode.EdgeHandleAnchors;
        addIntermediatePoints();
    }

    @Override
    public void disableModificationFeatures() {
        mAnchorDragMode = AnchorDragMode.None;
        removeIntermediatePoints();
    }

    @Override
    public boolean isEditHandle(Anchor anchor) {
        return Ceiling.isEdgeHandleAnchor(anchor); // Ceiling anchors A, B, C are not visible in edit behavior to prevent them from interfering with edge handle anchors
    }

    public AnchorDragMode getAnchorDragMode() {
        return mAnchorDragMode;
    }

    public void setAnchorDragMode(AnchorDragMode value) {
        mAnchorDragMode = value;
    }

    @Override
    public boolean isAnchorDragSupported(Anchor anchor) {
        return mAnchorDragMode.canDragAnchor(anchor);
    }

    @Override
    public void updateScale(double scaleCompensation) {
        super.updateScale(scaleCompensation);
        updatePoints();
        if (mIntermediatePoints == null) {
            // No intermediate points visible ATM
            return;
        }
        for (IntermediatePoint ip : mIntermediatePoints) {
            ip.updateScale(scaleCompensation);
        }
    }

    @Override
    public void updateToModel() {
        super.updateToModel();
        updatePoints();
        updateIntermediatePoints();
        updateProperties();
    }

    @Override
    protected Optional<Shape> getShapeForIntersectionCheck() {
        return Optional.of(mShape);
    }
}
