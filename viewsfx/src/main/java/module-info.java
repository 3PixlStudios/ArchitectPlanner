/*******************************************************************************
 *     Architect - A free 2D/3D home and interior designer
 *     Copyright (C) 2021 - 2023  Daniel Höh
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
module de.dh.utils.fx.viewsfx {
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires org.slf4j;
    requires java.xml.bind;

    exports de.dh.utils.fx.viewsfx;
    exports de.dh.utils.fx.viewsfx.io;
    exports de.dh.utils.fx.viewsfx.state;
    exports de.dh.utils.fx.viewsfx.layout;
    exports de.dh.utils.fx.sash;
    opens de.dh.utils.fx.viewsfx.io;
    opens de.dh.utils.fx.viewsfx.state;
}