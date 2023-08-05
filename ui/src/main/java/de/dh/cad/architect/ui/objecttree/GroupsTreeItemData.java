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
package de.dh.cad.architect.ui.objecttree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import de.dh.cad.architect.model.objects.BaseObject;

/**
 * Represents the data of a tree item in the object groups tree.
 */
public class GroupsTreeItemData implements IObjectTreeItemData {
    protected final BaseObject mObject;

    public GroupsTreeItemData(BaseObject object) {
        mObject = object;
    }

    @Override
    public Optional<Boolean> isVisible() {
        return Optional.of(!mObject.isHidden());
    }

    @Override
    public Collection<BaseObject> getObjects() {
        return Arrays.asList(mObject);
    }

    @Override
    public boolean isShadowEntry() {
        return true;
    }

    @Override
    public String getTitle() {
        return mObject.getClass().getSimpleName();
    }

    @Override
    public String getId() {
        return mObject.getId();
    }

    @Override
    public BaseObject getObject() {
        return mObject;
    }
}
