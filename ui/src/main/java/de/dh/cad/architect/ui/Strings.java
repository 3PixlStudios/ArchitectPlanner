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
package de.dh.cad.architect.ui;

import de.dh.cad.architect.model.wallmodel.WallBevelType;
import javafx.util.StringConverter;

public class Strings {
    public static final String APPLICATION_NAME = "Architect";

    public static final String NEW_PLAN_FILE = "Neuer Plan";

    // Common strings
    public static final String YES = "Ja";
    public static final String NO = "Nein";
    public static final String OK = "Ok";
    public static final String CANCEL = "Abbrechen";
    public static final String ALL_FILES_EXTENSION_FILTER_NAME = "Alle Dateien";

    public static final String BUTTON_REMOVE = "_Entfernen";
    public static final String BUTTON_RENAME = "_Umbenennen";

    public static final String ERROR_LOADING_DATA = "Fehler beim Laden der Daten. Details siehe Log-Datei.";

    public  static final String NO_ENTRY_CHOOSEN = "Kein Eintrag ausgewählt";

    // Dialogs
    public static final String DIVIDE_WALL_LENGTH_DIALOG_TITLE = "Wand teilen";
    public static final String DIVIDE_WALL_LENGTH_DIALOG_HEADER = "Teilt die Wand in zwei verbundene Wandteile.\nWähle die Position, an der die Wand geteilt werden soll!";

    // Anchor dock info control
    public static final String ANCHOR_DOCK_INFO_OWNER_COLUMN = "Objekt";
    public static final String ANCHOR_DOCK_INFO_ANCHOR_COLUMN = "Anker";
    public static final String ANCHOR_DOCK_INFO_HINT_LABEL = "Untergeordnete Anker sind an übergeordnete Anker angedockt. Der Wurzelknoten ist der Haupt-Anker des Anker-Docks und bestimmt dessen Position.";

    public static String ERRONEOUS_VALUE = "<?>";

    public static final String MITER_STRING = "Spitz";
    public static final String BEVEL_STRING = "Abgeschrägt";

    // Enum string providers
    public static final StringConverter<WallBevelType> WALL_BEVEL_TYPE_TITLE_PROVIDER = new StringConverter<>() {
        @Override
        public String toString(WallBevelType wallBevel) {
            switch (wallBevel) {
            case Miter: return MITER_STRING;
            case Bevel: return BEVEL_STRING;
            }
            throw new RuntimeException("No title string found for wall bevel type " + wallBevel);
        }

        @Override
        public WallBevelType fromString(String str) {
            switch (str) {
            case MITER_STRING : return WallBevelType.Miter;
            case BEVEL_STRING : return WallBevelType.Bevel;
            }
            throw new RuntimeException("No wall bevel type found for string " + str);
        }
    };

    public static final String PERSPECTIVE_RENDERING = "Perspektivisch";
    public static final String PARALLEL_RENDERING = "Orthografisch";

    public static final String ANCHOR_NAME_INFO_ID = "Anker-Id: {0}";
    public static final String ANCHOR_NAME_INFO_TYPE = "Anker-Typ: {0}";
    public static final String ANCHOR_NAME = "Anker von {0} ({1})";

    public static final String OBJECT_TYPE_NAME_ANCHOR_S = "Anker";
    public static final String OBJECT_TYPE_NAME_ANCHOR_P = "Anker";
    public static final String OBJECT_TYPE_NAME_CEILING_S = "Decke";
    public static final String OBJECT_TYPE_NAME_CEILING_P = "Decken";
    public static final String OBJECT_TYPE_NAME_COVERING_S = "Abdeckung";
    public static final String OBJECT_TYPE_NAME_COVERING_P = "Abdeckungen";
    public static final String OBJECT_TYPE_NAME_DIMENSIONING_S = "Bemaßung";
    public static final String OBJECT_TYPE_NAME_DIMENSIONING_P = "Bemaßungen";
    public static final String OBJECT_TYPE_NAME_FLOOR_S = "Boden";
    public static final String OBJECT_TYPE_NAME_FLOOR_P = "Böden";
    public static final String OBJECT_TYPE_NAME_GUIDE_LINE_S = "Hilfslinie";
    public static final String OBJECT_TYPE_NAME_GUIDE_LINE_P = "Hilfslinien";
    public static final String OBJECT_TYPE_NAME_OBJECTS_GROUP_S = "Objektgruppe";
    public static final String OBJECT_TYPE_NAME_OBJECTS_GROUP_P = "Objektgruppen";
    public static final String OBJECT_TYPE_NAME_SUPPORT_OBJECT_S = "Hilfsobjekt";
    public static final String OBJECT_TYPE_NAME_SUPPORT_OBJECT_P = "Hilfsobjekte";
    public static final String OBJECT_TYPE_NAME_WALL_S = "Wand";
    public static final String OBJECT_TYPE_NAME_WALL_P = "Wände";
    public static final String OBJECT_TYPE_NAME_WALL_HOLE_S = "Wandöffnung";
    public static final String OBJECT_TYPE_NAME_WALL_HOLE_P = "Wandöffnungen";

    // Main window
    public static final String ACTION_OPEN_ROOT_PLAN = "Plan öffnen...";
    public static final String MAIN_WINDOW_TITLE = APPLICATION_NAME;
    public static final String DIALOG_SAVE_PLAN_TITLE = "Plan speichern";
    public static final String DIALOG_SAVE_PLAN_ON_CLOSE_TITLE = "Plan schließen";
    public static final String DIALOG_SAVE_PLAN_ON_QUIT_HEADER = "Geänderten Plan speichern?";
    public static final String DIALOG_OPEN_PLAN_TITLE = "Plan öffnen";
    public static final String FILE_TYPE_ROOT_PLAN_EXTENSION_NAME = "Architect Plan";

    public static final String PLAN_FILES_HISTORY_CLEAR_HISTORY_MENU_ITEM = "History löschen";

    // Actions
    public static final String UNGROUP_ACTION_TITLE = "Gruppe auflösen";
    public static final String GROUP_N_OBJECTS_ACTION_TITLE = "{0} Objekte gruppieren";
    public static final String GROUP_ACTION_EDIT_GROUP_NAME_DIALOG_TITLE = "Gruppenname eingeben";
    public static final String GROUP_ACTION_EDIT_GROUP_NAME_DIALOG_HEADER_TEXT = "Gib einen Namen für die neue Gruppe ein";
    public static final String GROUP_ACTION_EDIT_GROUP_NAME_DIALOG_CONTENT_TEXT = "Gruppenname";

    public static final String PERMANENT_DOCK_ANCHOR_ACTION_TITLE = "Anker andocken";
    public static final String ACTION_CANCEL_PERMANENT_DOCK = "Abbrechen: Andocken";
    public static final String UNDOCK_ANCHOR_ACTION_TITLE = "Anchor abdocken";
    public static final String ACTION_CANCEL_UNDOCK = "Abbrechen: Abdocken";
    public static final String ACTION_PERMANENT_DOCK_TO_SELECTED_ANCHOR_TITLE = "An selektierten Anker ''{0}'' andocken";
    public static final String SOFT_DOCK_ANCHOR_ACTION_TITLE = "Ankerposition angleichen";
    public static final String ACTION_CANCEL_SOFT_DOCK = "Abbrechen: Ankerposition angleichen";
    public static final String ACTION_SOFT_DOCK_TO_SELECTED_ANCHOR_TITLE = "Position an selektierten Anker ''{0}'' angleichen";

    public static final String ACTION_STRAIGHTEN_WALL_BEND_POINT_TITLE = "Wandecke entfernen";
    public static final String ACTION_REMOVE_FLOOR_CORNER_TITLE = "Fußboden-Ecke entfernen";
    public static final String ACTION_REMOVE_CEILING_CORNER_TITLE = "Decken-Ecke entfernen";
    public static final String ACTION_EDIT_CEILING_ANCHORS_TITLE = "Deckenaufhängung bearbeiten";
    public static final String EDIT_CEILING_ANCHORS_USER_HINT = "Ändere die Positon eines Decken-Ankers oder docke ihn an einen anderen Anker an";
    public static final String EDIT_CEILING_ANCHORS_BEHAVIOR_TITLE = "Deckenaufhängung bearbeiten";

    // Properties
    public static final String PROPERTIES_NO_OBJECT_SELECTED = "Kein Objekt ausgewählt";
    public static final String OBJECTS_SELECTED_X = "Ausgewählt: {0}";
    public static final String PROPERTIES_NO_PROPERTIES_TO_SHOW = "Keine Eigenschaften zum Anzeigen";

    public static final String CEILING_HEIGHT_FROM_TO = "{0} bis {1}";

    // Ground plan
    public static final String BEHAVIORS_OBJ_NAME = "{0}";

    public static final String CEILING_MODE_ACTION_TOOLTIP = "Decken-Modus";
    public static final String ADD_CEILING_BEHAVIOR_TITLE = "Decken-Modus";
    public static final String ADD_CEILING_BEHAVIOR_USER_HINT = "Wähle drei Anker am oberen Wandende, um die Decke aufzuhängen";

    public static final String CONSTRUCTION_SELECTION_MODE_ACTION_TOOLTIP = "Selektionsmodus";
    public static final String CONSTRUCTION_SELECTION_BEHAVIOR_TITLE = "Selektionsmodus";
    public static final String CONSTRUCTION_SELECTION_BEHAVIOR_USER_HINT = "Selektiere Objekte durch Klicken";

    public static final String CONSTRUCTION_MODE_ACTION_TOOLTIP = "Konstruktionsmodus";
    public static final String CONSTRUCTION_BEHAVIOR_TITLE = "Konstruktionsmodus";
    public static final String CONSTRUCTION_BEHAVIOR_USER_HINT = "Konstruiere deine Wohnung!";
    public static final String CONSTRUCTION_BEHAVIOR_USER_HINT_N_OBJECTS_SELECTED = "{0} Objekte ausgewählt";
    public static final String CONSTRUCTION_BEHAVIOR_CONTEXT_MENU_NAME = "Objektmenü";
    public static final String CONSTRUCTION_BEHAVIOR_TURN_VISIBLE_1 = "Objekt einblenden";
    public static final String CONSTRUCTION_BEHAVIOR_TURN_VISIBLE_N = "Objekte enblenden";
    public static final String CONSTRUCTION_BEHAVIOR_TURN_INVISIBLE_1 = "Objekt ausblenden";
    public static final String CONSTRUCTION_BEHAVIOR_TURN_INVISIBLE_N = "Objekte ausblenden";

    public static final String DRAG_ELEVATION = "Abstand vom Boden: {0}";
    public static final String DRAG_HEIGHT = "Höhe: {0}";

    // Support objects
    public static final String SUPPORT_OBJECTS_MODE_BEHAVIOR_TITLE = "Möblierung und Hilfsobjekte";
    public static final String SUPPORT_OBJECTS_MODE_ACTION_TOOLTIP = "Einrichtung platzieren";
    public static final String ACTION_GOUND_PLAN_ADD_SUPPORT_OBJECT_TITLE = "Hilfsobjekt hinzufügen";
    public static final String ACTION_GOUND_PLAN_CANCEL_ADD_SUPPORT_OBJECT_TITLE = "Abbrechen: Hilfsobjekt platzieren";
    public static final String ADD_SUPPORT_OBJECT_BEHAVIOR_DEFAULT_USER_HINT = "Platziere das Hilfsobjekt";
    public static final String ADD_SUPPORT_OBJECT_BEHAVIOR_TITLE = "Hilfsobjekt platzieren";

    public static final String ACTION_GOUND_PLAN_COPY_SUPPORT_OBJECT_TITLE = "Hilfsobjekt {0} kopieren";
    public static final String ACTION_GOUND_PLAN_COPY_SUPPORT_OBJECTS_TITLE = "{0} Hilfsobjekte kopieren";

    public static final String ADD_SUPPORT_OBJECT_BEHAVIOR_ERROR_SUPPORT_OBJECT_BROKEN_TITLE = "Fehler";
    public static final String ADD_SUPPORT_OBJECT_BEHAVIOR_ERROR_SUPPORT_OBJECT_BROKEN_HEADER = "Das Objekt kann nicht geladen werden";
    public static final String ADD_SUPPORT_OBJECT_BEHAVIOR_ERROR_SUPPORT_OBJECT_BROKEN_CONTENT = "Es gab einen Fehler beim Laden des Objektes. Überprüfe das Objekt in der Asset-Bibliothek bzw. überprüfe das Log.";

    // Construction view
    public static final String CONSTRUCTION_VIEW_TITLE = "Konstruktion";

    // Common behavior actions
    public static final String ACTION_GROUND_PLAN_JOIN_WALLS_TITLE = "Wände {0} und {1} zusammenfügen";

    // GroundPlan Default Behavior
    public static final String GROUNDPLAN_DEFAULT_BEHAVIOR_TITLE = "2D-Konstruktionsmodus";

    // Add Wall Behavior
    public static final String ACTION_GROUND_PLAN_CREATE_WALL_TITLE = "Neue Wand erstellen";
    public static final String ACTION_GROUND_PLAN_CONTINUE_WALL_TITLE = "Wand fortsetzen";
    public static final String ACTION_GROUND_PLAN_CANCEL_CREATE_WALL_TITLE = "Abbrechen: Neue Wand";
    public static final String GROUND_PLAN_START_WALL_BEHAVIOR_TITLE = "Wand hinzufügen";
    public static final String GROUND_PLAN_START_WALL_BEHAVIOR_DEFAULT_USER_HINT = "Wähle den Startpunkt der Wand";
    public static final String GROUND_PLAN_CREATE_WALL_BEHAVIOR_ANCHOR_AIMED = "Wand anschließen an {0}";
    public static final String GROUND_PLAN_CREATE_WALL_BEHAVIOR_START_POSITION_AIMED = "Wandende bei Position {0}";
    public static final String GROUND_PLAN_CREATE_WALL_BEHAVIOR_BREAK_WALL = "An vorhandener Wand anschließen";

    public static final String ACTION_GROUND_PLAN_CANCEL_ADD_WALL_TITLE = "Abbrechen: Neue Wand";
    public static final String GROUND_PLAN_ADD_WALL_BEHAVIOR_TITLE = "Wand hinzufügen";
    public static final String GROUND_PLAN_ADD_WALL_BEHAVIOR_DEFAULT_USER_HINT = "Platziere die Wand";
    public static final String GROUND_PLAN_ADD_WALL_BEHAVIOR_ANCHOR_AIMED = "Wand anschließen an {0}";
    public static final String GROUND_PLAN_ADD_WALL_BEHAVIOR_END_POSITION_AIMED = "Wand ziehen bis {0}";
    public static final String GROUND_PLAN_START_WALL_BEHAVIOR_INTERACTIONS_TAB_TITLE = "Neue Wand erstellen";
    public static final String GROUND_PLAN_ADD_WALL_BEHAVIOR_INTERACTIONS_TAB_TITLE = "Neue Wand erstellen";

    // Add Dimensioning Behavior
    public static final String ACTION_GROUND_PLAN_ADD_DIMENSIONING_TITLE = "Bemaßung hinzufügen";
    public static final String ACTION_GROUND_PLAN_CANCEL_CREATE_DIMENSIONING_TITLE = "Abbrechen: Bemaßung einfügen";
    public static final String GROUND_PLAN_START_DIMENSIONING_BEHAVIOR_TITLE = "Bemaßung hinzufügen";
    public static final String GROUND_PLAN_START_DIMENSIONING_BEHAVIOR_DEFAULT_USER_HINT = "Wähle den Start-Anker der Bemaßung";
    public static final String GROUND_PLAN_START_DIMENSIONING_BEHAVIOR_ANCHOR_AIMED = "Bemaßung starten bei {0}";

    public static final String ACTION_GROUND_PLAN_CANCEL_ADD_DIMENSIONING_TITLE = "Abbrechen: Bemaßung einfügen";
    public static final String GROUND_PLAN_ADD_DIMENSIONING_BEHAVIOR_TITLE = "Bemaßung hinzufügen";
    public static final String GROUND_PLAN_ADD_DIMENSIONING_BEHAVIOR_DEFAULT_USER_HINT = "Wähle den End-Anker der Bemaßung";
    public static final String GROUND_PLAN_ADD_DIMENSIONING_BEHAVIOR_ANCHOR_AIMED = "Bemaßung enden bei {0}";

    public static final String ACTION_GROUND_PLAN_REMOVE_OBJECTS_TITLE = "{0} Objekt(e) entfernen";
    public static final String ACTION_GROUND_PLAN_REMOVE_SINGLE_OBJECT_TITLE = "{0} entfernen";

    // Edit Object / Edit Anchor Behavior
    public static final String GROUND_PLAN_EDIT_OBJECT_BEHAVIOR_TITLE = "Objekt bearbeiten";
    public static final String GROUND_PLAN_EDIT_ANCHOR_BEHAVIOR_TITLE = "Anker bearbeiten";
    public static final String GROUND_PLAN_EDIT_OBJECT_USER_HINT = "{0} ist ausgewählt";
    public static final String GROUND_PLAN_EDIT_ANCHOR_USER_HINT = "{0} ist ausgewählt";
    public static final String ACTION_GROUND_PLAN_DIVIDE_WALL_LENGTH_TITLE = "Wand teilen";
    public static final String ACTION_GROUND_PLAN_ADD_WALL_HOLE_TITLE = "Wandöffnung einfügen";
    public static final String ACTION_GROUND_PLAN_REMOVE_WALL_HOLE_TITLE = "Wandöffnung entfernen";

    // Add Floor Behavior
    public static final String ACTION_GROUND_PLAN_ADD_FLOOR_TITLE = "Fußboden einfügen";
    public static final String ACTION_GROUND_PLAN_CANCEL_ADD_FLOOR_TITLE = "Abbrechen: Fußboden einfügen";
    public static final String GROUND_PLAN_ADD_FLOOR_BEHAVIOR_TITLE = "Fußboden hinzufügen";
    public static final String GROUND_PLAN_ADD_FLOOR_BEHAVIOR_DEFAULT_USER_HINT = "Platziere die Eckpunkte des Fußbodens";
    public static final String GROUND_PLAN_ADD_FLOOR_BEHAVIOR_ANCHOR_AIMED = "Boden anschließen an {0}";
    public static final String GROUND_PLAN_ADD_FLOOR_BEHAVIOR_POSITION_AIMED = "Boden-Eckpunkt setzen bei {0}";

    // Add Ceiling
    public static final String ACTION_GROUND_PLAN_ADD_CEILING_TITLE = "Decke einfügen";
    public static final String ACTION_GROUND_PLAN_ADD_CEILING_BETWEEN_ANCHORS_TITLE = "Decke zwischen {0}, {1} und {2} einfügen";
    public static final String ACTION_GROUND_PLAN_CANCEL_ADD_CEILING_TITLE = "Abbrechen: Decke einfügen";
    public static final String GROUND_PLAN_ADD_CEILING_BEHAVIOR_TITLE = "Decke hinzufügen";
    public static final String GROUND_PLAN_ADD_CEILING_BEHAVIOR_DEFAULT_USER_HINT = "Platziere die 3 Stützpunkte der Decke";
    public static final String GROUND_PLAN_ADD_CEILING_BEHAVIOR_ANCHOR_AIMED = "Stützpunkt setzen bei {0}";

    // Add Covering
    public static final String ACTION_GROUND_PLAN_ADD_COVERING_BETWEEN_ANCHORS_TITLE = "Abdeckung zwischen {0}, {1} und {2} einfügen";

    // Edit Selected Anchor
    public static final String INTERACTIONS_TAB_SELECTED_ANCHOR_TITLE = "Anker-Dockhierarchie";

    // Dock Behaviors
    public static final String INTERACTIONS_TAB_PERMANENT_DOCK_OPERATION_TITLE = "Anker verbinden";
    public static final String DOCK_OPERATION_DESCRIPTION = "Festlegen der Parameter";
    public static final String INTERACTIONS_TAB_SOFT_DOCK_OPERATION_TITLE = "Angleich-Operation";
    public static final String DOCK_OPERATION_SOURCE_OBJECT = "Objekt:";
    public static final String DOCK_OPERATION_SOURCE_ANCHOR = "Anzupassender Anker:";
    public static final String DOCK_OPERATION_TARGET_OBJECT = "Ziel-Objekt:";
    public static final String DOCK_OPERATION_TARGET_ANCHOR = "Ziel-Anker:";
    public static final String DOCK_OPERATION_NO_TARGET_OBJECT = "Kein gültiges Ziel-Objekt ausgewählt";
    public static final String DOCK_OPERATION_NO_TARGET_ANCHOR = "Kein gültiger Ziel-Anker ausgewählt";
    public static final String DOCK_OPERATION_CHOOSE_TARGET_OBJECT_0 = "Klicken, um Ziel-Objekt {0} auszuwählen";
    public static final String DOCK_OPERATION_TARGET_ANCHOR_0 = "Klicken, um Ziel-Anker {0} auszuwählen";

    // Permanent Dock Behavior
    public static final String PERMANENT_DOCK_BEHAVIOR_TITLE = "Anker permanent andocken";
    public static final String PERMANENT_DOCK_BEHAVIOR_USER_HINT = "Wähle einen Objektanker, an den der Anker {0} angedockt werden soll";
    public static final String DOCK_OPERATION_PERMANENT_DOCK_TO_TARGET_ANCHOR = "An Anker andocken";

    // Soft Dock Behavior
    public static final String SOFT_DOCK_BEHAVIOR_TITLE = "Ankerposition angleichen";
    public static final String SOFT_DOCK_BEHAVIOR_USER_HINT = "Wähle einen Objektanker, dessen Position für Anker {0} übernommen werden soll";
    public static final String DOCK_OPERATION_SOFT_DOCK_TO_TARGET_ANCHOR = "Anker-Position übernehmen";

    // Edit Support Object Behavior
    public static final String INTERACTIONS_TAB_SELECTED_SUPPORT_OBJECT_TITLE = "Hilfsobjekt(e) bearbeiten";
    public static final String SUPPORT_OBJECTS_EDIT_BEHAVIOR_USER_HINT = "Ordne die Hilfsobjekte an oder verändere sie";
    public static final String SUPPORT_OBJECTS_EDIT_BEHAVIOR_TITLE = "Hilfsobjekt(e) bearbeiten";

    // 3D plan
    public static final String THREE_D_PLAN_VIEW_TITLE = "3D-Ansicht";

    public static final String THREE_D_MENU = "3D-Ansicht";
    public static final String THREE_D_MENU_CAMERA_POSITIONS = "Kamerapositionen";
    public static final String THREE_D_MENU_CAMERA_POSITIONS_SAVE_CURRENT = "Aktuelle Position speichern...";
    public static final String THREE_D_MENU_CAMERA_POSITIONS_MANAGE_POSITIONS = "Positionen verwalten...";
    public static final String THREE_D_SAVE_CAMERA_DIALOG_TITLE = "Kameraposition speichern...";
    public static final String THREE_D_SAVE_CAMERA_DIALOG_HEADER = "Gib einen Namen für die Kameraposition ein";
    public static final String THREE_D_SAVE_CAMERA_DIALOG_CAMERA_NAME_LABEL = "Kameraname:";
    public static final String THREE_D_SAVE_CAMERA_NAME_PATTERN = "Kamera {0}";
    public static final String THREE_D_INVALID_CAMERA_NAME_DIALOG_TITLE = "Ungültiger Kameraname";
    public static final String THREE_D_INVALID_CAMERA_NAME_DIALOG_NAME_ALREADY_EXISTS_TEXT = "Der Kameraname {0} ist schon vergeben";

    public static final String THREE_D_CAMERA_POSITIONS_MANAGER_DIALOG_TITLE = "Kamerapositionen verwalten";
    public static final String THREE_D_CAMERA_POSITIONS_MANAGER_NAME_CAMERA_POSITION_DIALOG_TITLE = "Kameraposition umbenennen";
    public static final String THREE_D_CAMERA_POSITIONS_MANAGER_NAME_CAMERA_POSITION_DIALOG_HEADER = "Gib einen neuen Namen für die Kameraposition ein";
    public static final String THREE_D_CAMERA_POSITIONS_MANAGER_NAME_CAMERA_POSITION_DIALOG_CAMERA_NAME_LABEL = "Neuer Name:";

    public static final String THREE_D_SELECTION_MODE_ACTION_TOOLTIP = "Selektionsmodus";
    public static final String THREE_D_SELECTION_BEHAVIOR_TITLE = "Selektionsmodus";
    public static final String THREE_D_SELECTION_BEHAVIOR_USER_HINT = "Selektiere Objekte durch Klicken";

    public static final String PAINTER_MODE_ACTION_TOOLTIP = "Oberflächen bearbeiten";
    public static final String THREE_D_PAINTER_BEHAVIOR_TITLE = "Objektoberflächen-Bearbeitungsmodus";
    public static final String THREE_D_PAINTER_BEHAVIOR_USER_HINT = "Trage das gewählte Material durch Klicken auf eine Objektoberfläche auf. Halte <Strg> zum Kopieren des angeklickten Materials.";
    public static final String THREE_D_PAINTER_BEHAVIOR_MATERIAL_CONTROL_TITLE = "Aufzutragendes Material";
    public static final String THREE_D_PAINTER_BEHAVIOR_NO_MATERIAL_CHOOSEN = "- Keine Material gewählt -";
    public static final String THREE_D_PAINTER_BEHAVIOR_CHOOSE_MATERIAL_DIALOG_TITLE = "Material auswählen";
    public static final String THREE_D_PAINTER_BEHAVIOR_SURFACE_HINT = "Oberfläche ''{1}'' von {0}";
    public static final String THREE_D_PAINTER_BEHAVIOR_UNABLE_TO_ASSIGN_MATERIAL = "{0} scheint einen inkonsistenten Zustand zu haben. Das kann während der Weiterentwicklung am Objekt-Ladecode passieren. Versuche, das Objekt zurückzusetzen.";

    public static final String ACTION_THREE_D_RESET_SUPPORT_OBJECT_SURFACES = "Objektoberflächen zurücksetzen/reparieren";
    public static final String THREE_D_RESET_SUPPORT_OBJECT_SURFACES_BEHAVIOR_TITLE = "Oberflächen von Hilfsobjekten zurücksetzen";
    public static final String THREE_D_RESET_SUPPORT_OBJECT_SURFACES_BEHAVIOR_USER_HINT = "Klicke auf ein Hilfsobjekt, um seine Oberflächen zurückzusetzen";
    public static final String CANCEL_RESET_SUPPORT_OBJECT_SURFACES_BEHAVIOR = "Fertig: Oberflächen zurücksetzen";

    // Library Manager
    public static final String LIBRARY_MANAGER_LOADING_MATERIAL_SETS_PROGRESS_TITLE = "Lade Material-Kollektionen";
    public static final String LIBRARY_MANAGER_LOADING_SUPPORT_OBJECTS_PROGRESS_TITLE = "Lade Hilfsobjekte";
    public static final String LIBRARY_MANAGER_WINDOW_TITLE = APPLICATION_NAME + " - Bibliotheksverwaltung";
    public static final String LIBRARY_MANAGER_NEW_ASSET_LIBRARY_DIALOG_TITLE = "Leeres Verzeichnis für neue Bibliothek wählen";
    public static final String LIBRARY_MANAGER_IMPORT_DIALOG_TITLE = "Bibliotheken öffnen";
    public static final String LIBRARY_MANAGER_IMPORT_DIALOG_HEADER = "Wähle Bibliotheken vom Datenträger aus";
    public static final String LIBRARY_MANAGER_IMPORT_OPEN_BUTTON_TITLE = "Öffnen";
    public static final String LIBRARY_MANAGER_IMPORT_NO_DIRECTORY = "Wähle ein Verzeichnis aus";
    public static final String LIBRARY_MANAGER_IMPORT_NO_LIBRARIES_FOUND = "Keine Bibliotheken unter Verzeichnis ''{0}''";
    public static final String LIBRARY_MANAGER_IMPORT_ERROR_READING_LIBRARIES = "Fehler beim Laden von Bibliotheksdaten aus Verzeichnis ''{0}''";
    public static final String LIBRARY_MANAGER_LOAD_ASSET_LIBRARIES_DIALOG_TITLE = "Bibliotheksverzeichnis wählen";
    public static final String LIBRARY_MANAGER_NEW_ASSET_LIBRARY_NAME = "Asset-Bibliothek";
    public static final String NEW_MATERIAL_SET_NAME_PATTERN = "Material-Kollektion {0}";
    public static final String SELECT_TEXTURE_IMAGE_DIALOG_TITLE = "Textur für Material wählen";
    public static final String NEW_TEXTURE_NAME = "Textur {0}";
    public static final String NEW_SUPPORT_OBJECT_NAME_PATTERN = "Hilfsobjekt {0}";
    public static final String SELECT_ICON_IMAGE_DIALOG_TITLE = "Objekt-Icon wählen";
    public static final String SELECT_PLAN_VIEW_IMAGE_DIALOG_TITLE = "Objekt-Ansicht im Grundriss wählen";
    public static final String SELECT_OBJECT_3D_RESOURCE_DIALOG_TITLE = "3D-Ressource für Objekt wählen";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_LIBRARIES_TITLE = "Bibliotheken löschen";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_LIBRARIES_HEADER_1 = "Soll die Bibliothek {0} gelöscht werden?";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_LIBRARIES_HEADER_N = "Sollen die Bibliotheken {0} gelöscht werden?";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_MATERIAL_SET_TITLE = "Material-Kollektion löschen";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_MATERIAL_SET_HEADER_1 = "Soll die Material-Kollektion {0} gelöscht werden?";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_MATERIAL_SET_HEADER_N = "Sollen die Material-Kollektionen {0} gelöscht werden?";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_SUPPORT_OBJECTS_TITLE = "Hilfsobjekte löschen";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_SUPPORT_OBJECTS_HEADER_1 = "Soll das Hilfsobjekt {0} gelöscht werden?";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_SUPPORT_OBJECTS_HEADER_N = "Sollen die Hilfsobjekte {0} gelöscht werden?";
    public static final String LIBRARY_MANAGER_DIALOG_QUERY_DELETE_OBJECTS_COMMON_TEXT = "Die Dateien werden unwiderruflich vom Datenträger gelöscht";

    public static final String LIBRARY_MANAGER_NEW_LIBRARY_TOOLTIP = "Neue Bibliothek anlegen...";
    public static final String LIBRARY_MANAGER_EDIT_LIBRARY_TOOLTIP = "Bibliothek {0} bearbeiten...";
    public static final String LIBRARY_MANAGER_REMOVE_LIBRARY_1_TOOLTIP = "Bibliothek {0} entfernen. Die Bibliothek bleibt auf dem Datenträger erhalten.";
    public static final String LIBRARY_MANAGER_REMOVE_LIBRARY_N_TOOLTIP = "{0} Bibliotheken entfernen. Die Bibliotheken bleiben auf dem Datenträger erhalten.";
    public static final String LIBRARY_MANAGER_DELETE_LIBRARY_1_TOOLTIP = "Bibliothek löschen. Die Bibliothek wird auf dem Datenträger gelöscht.";
    public static final String LIBRARY_MANAGER_DELETE_LIBRARY_N_TOOLTIP = "{0} Bibliotheken löschen. Die Bibliotheken werden auf dem Datenträger gelöscht.";

    public static final String LIBRARY_MANAGER_NEW_MATERIAL_SET_TOOLTIP = "Neue Material-Kollektion anlegen";
    public static final String LIBRARY_MANAGER_EDIT_MATERIAL_SET_1_TOOLTIP = "Material-Kollektion {0} bearbeiten";
    public static final String LIBRARY_MANAGER_EDIT_MATERIAL_SETS_N_TOOLTIP = "Material-Kollektionen {0} bearbeiten";
    public static final String LIBRARY_MANAGER_DELETE_MATERIAL_SET_1_TOOLTIP = "Material-Kollektion {0} löschen";
    public static final String LIBRARY_MANAGER_DELETE_MATERIAL_SETS_N_TOOLTIP = "{0} Material-Kollektionen löschen";

    public static final String LIBRARY_MANAGER_NEW_SUPPORT_OBJECT_TOOLTIP = "Neues Hilfsobjekt anlegen";
    public static final String LIBRARY_MANAGER_EDIT_SUPPORT_OBJECT_1_TOOLTIP = "Hilfsobjekt {0} bearbeiten";
    public static final String LIBRARY_MANAGER_EDIT_SUPPORT_OBJECTS_N_TOOLTIP = "Hilfsobjekte {0} bearbeiten";
    public static final String LIBRARY_MANAGER_DELETE_SUPPORT_OBJECT_1_TOOLTIP = "Hilfsobjekt {0} löschen";
    public static final String LIBRARY_MANAGER_DELETE_SUPPORT_OBJECTS_N_TOOLTIP = "{0} Hilfsobjekte löschen";

    public static final String LIBRARY_MANAGER_NO_MATERIAL_SET_IN_TABLE_HINT = "Keine Material-Kollektionen in Auswahl";
    public static final String LIBRARY_MANAGER_NO_SUPPORT_OBJECTS_IN_TABLE_HINT = "Keine Hilfsobjekte in Auswahl";
    public static final String LIBRARY_MANAGER_NO_ASSET_SELECTED = "Kein Objekt ausgewählt";
    public static final String LIBRARY_MANAGER_N_ASSETS_OF_TYPE_SELECTED = "{0} Objekte vom Typ {1} ausgewählt";

    public static final String LIBRARY_MANAGER_ASSET_PATH_ANCHOR_PLAN = "Plan";
    public static final String LIBRARY_MANAGER_ASSET_PATH_ANCHOR_LIBRARY = "Bibliothek {0}";
    public static final String LIBRARY_MANAGER_ASSET_PATH_ANCHOR_SUPPORT_OBJECT = "Hilfsobjekt {0}";

    public static final String LIBRARY_MANAGER_CREATE_MATERIAL_SET_DIALOG_TITLE = "Neue Material-Kollektion anlegen";
    public static final String LIBRARY_MANAGER_CREATE_MATERIAL_SET_HEADER_DIALOG_HEADER = "Wähle die Bibliothek und ggf. das Hilfsobjekt, in dem die neue Material-Kollektion erstellt werden soll";
    public static final String LIBRARY_MANAGER_CREATE_SUPPORT_OBJECT_DIALOG_TITLE = "Hilfsobjekt anlegen";

    public static final String LIBRARY_MANAGER_EDIT_LIBRARY_DIALOG_TITLE = "Bibliothek bearbeiten";
    public static final String LIBRARY_MANAGER_EDIT_LIBRARY_DIALOG_HEADER = "Trage die Stammdaten der Bibliothek ein";

    public static final String LIBRARY_MANAGER_EDIT_N_OBJECTS_DIALOG_TITLE = "Objekte bearbeiten";
    public static final String LIBRARY_MANAGER_EDIT_N_OBJECTS_DIALOG_HEADER = "Verändere einzelne Felder in {0} Objekten gleichzeitig";

    public static final String LIBRARY_MANAGER_EDIT_MATERIAL_SET_DIALOG_TITLE = "Material-Kollektion bearbeiten";
    public static final String LIBRARY_MANAGER_EDIT_MATERIAL_SET_DIALOG_HEADER = "Konfiguriere die Eigenschaften der Material-Kollektion";

    public static final String LIBRARY_MANAGER_EDIT_SUPPORT_OBJECT_DIALOG_TITLE = "Hilfsobjekt bearbeiten";
    public static final String LIBRARY_MANAGER_EDIT_SUPPORT_OBJECT_DIALOG_HEADER = "Konfiguriere die Eigenschaften des Hilfsobjekts";

    public static final String LIBRARY_MANAGER_ERROR_IMPORTING_RESOURCE_TITLE = "Fehler";
    public static final String LIBRARY_MANAGER_ERROR_IMPORTING_RESOURCE_HEADER = "Die Ressource konnte nicht importiert werden";

    public static final String LIBRARY_MANAGER_ERROR_IMPORTING_ICON_IMAGE_TEXT = "Fehler beim Import des Icons";
    public static final String LIBRARY_MANAGER_ERROR_IMPORTING_PLAN_VIEW_IMAGE_TEXT = "Fehler beim Import des Plan-Bildes";

    public static final String LIBRARY_MANAGER_ERROR_IMPORTING_3D_OBJECT_TEXT = "Fehler beim Import der 3D-Ressource";

    public static final String LIBRARY_MANAGER_EDIT_RAW_MATERIAL_SET_DIALOG_TITLE = "Materialien bearbeiten";
    public static final String LIBRARY_MANAGER_EDIT_RAW_MATERIAL_SET_DIALOG_HEADER = "Bearbeite Quelltext und Datei-Ressourcen der Material-Kollektion ''{0}''";

    public static final String LIBRARY_MANAGER_EDIT_SUPPORT_OBJECT_TAKE_SNAPSHOT_DIALOG_TITLE = "Bilder für Bibliothek aufnehmen";
    public static final String LIBRARY_MANAGER_EDIT_SUPPORT_OBJECT_TAKE_SNAPSHOT_ICON_IMAGE_SAVED = "Icon gespeichert";
    public static final String LIBRARY_MANAGER_EDIT_SUPPORT_OBJECT_TAKE_SNAPSHOT_PLAN_VIEW_IMAGE_SAVED = "Grundriss-Bild gespeichert";
}
