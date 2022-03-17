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
package de.dh.cad.architect.ui.view.libraries;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import de.dh.cad.architect.fx.nodes.objviewer.ThreeDObjectViewControl;
import de.dh.cad.architect.model.assets.AssetRefPath;
import de.dh.cad.architect.model.assets.SupportObjectDescriptor;
import de.dh.cad.architect.model.coords.Length;
import de.dh.cad.architect.model.coords.LengthUnit;
import de.dh.cad.architect.ui.Strings;
import de.dh.cad.architect.ui.assets.AssetLoader;
import de.dh.cad.architect.ui.assets.ThreeDObject;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

public class ShowSupportObjectControl extends BorderPane implements Initializable {
    public static final String FXML = "ShowSupportObjectControl.fxml";

    protected final AssetLoader mAssetLoader;
    protected final SupportObjectDescriptor mAssetDescriptor;

    @FXML
    protected TextField mAssetCollectionNameTextField;

    @FXML
    protected TextField mIdTextField;

    @FXML
    protected TextField mAssetRefPathTextField;

    @FXML
    protected TextField mNameTextField;

    @FXML
    protected TextArea mDescriptionTextArea;

    @FXML
    protected BorderPane mIconImagePane;

    @FXML
    protected ImageView mIconImageView;

    @FXML
    protected Label mWidthLabel;

    @FXML
    protected Label mHeightLabel;

    @FXML
    protected Label mDepthLabel;

    @FXML
    protected Label mElevationLabel;

    @FXML
    protected StackPane mThreeDResourceParent;

    protected ThreeDObjectViewControl mThreeDObjectView;

    public ShowSupportObjectControl(SupportObjectDescriptor descriptor, AssetLoader assetLoader) {
        mAssetLoader = assetLoader;
        mAssetDescriptor = descriptor;

        FXMLLoader fxmlLoader = new FXMLLoader(ShowSupportObjectControl.class.getResource(FXML));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String lengthOrError(Length length) {
        return length == null ? Strings.ERRONEOUS_VALUE : length.toHumanReadableString(LengthUnit.CM, true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AssetRefPath assetRefPath = mAssetDescriptor.getSelfRef();
        mAssetCollectionNameTextField.setText(assetRefPath.getAnchor().toString());
        mIdTextField.setText(mAssetDescriptor.getId());
        mAssetRefPathTextField.setText(assetRefPath.toPathString());
        mNameTextField.setText(StringUtils.trimToEmpty(mAssetDescriptor.getName()));
        mDescriptionTextArea.setText(StringUtils.trimToEmpty(mAssetDescriptor.getDescription()));

        updateIconImage();
        mThreeDObjectView = new ThreeDObjectViewControl();
        mThreeDObjectView.setPrefWidth(250);
        mThreeDObjectView.setPrefHeight(250);
        mThreeDObjectView.setCoordinateSystemVisible(true);
        mThreeDResourceParent.getChildren().add(mThreeDObjectView);

        mWidthLabel.setText("Breite (X): " + lengthOrError(mAssetDescriptor.getWidth()));
        mHeightLabel.setText("Höhe (Y): " + lengthOrError(mAssetDescriptor.getHeight()));
        mDepthLabel.setText("Tiefe (Z): " + lengthOrError(mAssetDescriptor.getDepth()));
        mElevationLabel.setText("Vertikaler Versatz (Y): " + lengthOrError(mAssetDescriptor.getElevation()));

        update3DObjectView();
    }

    protected void updateIconImage() {
        Image image = mAssetLoader.loadSupportObjectIconImage(mAssetDescriptor, true);
        mIconImageView.setImage(image);
    }

    protected void update3DObjectView() {
        SupportObjectDescriptor soDescriptor = mAssetDescriptor;
        ThreeDObject obj = mAssetLoader.loadSupportObject3DObject(soDescriptor, Optional.empty(), true);
        Group group = new Group();
        group.getChildren().addAll(obj.getMeshViews());
        Bounds boundsInParent = group.getBoundsInParent();
        Optional<Transform> oTrans = obj.getORootTransformation();
        ObservableList<Transform> transforms = group.getTransforms();
        transforms.add(new Translate(-boundsInParent.getCenterX(), -boundsInParent.getCenterY(), -boundsInParent.getCenterZ()));
        oTrans.ifPresent(trans -> transforms.add(trans));
        mThreeDObjectView.setObjView(group, 200);
    }
}
