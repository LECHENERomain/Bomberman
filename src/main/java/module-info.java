module fr.univartois.butinfo.r.bomberman{
    exports fr.univartois.butinfo.r304.bomberman;
    exports fr.univartois.butinfo.r304.bomberman.controller;
    exports fr.univartois.butinfo.r304.bomberman.model;
    exports fr.univartois.butinfo.r304.bomberman.view;


    opens fr.univartois.butinfo.r304.bomberman.controller to javafx.fxml;

    requires javafx.fxml;
    requires javafx.graphics;
    requires transitive javafx.controls;
}
