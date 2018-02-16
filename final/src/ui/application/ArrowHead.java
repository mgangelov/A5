package ui.application;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Transform;

class ArrowHead {
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private Polygon arrowhead;

    ArrowHead(double startX, double startY, double endX, double endY, double size) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        arrowhead = new Polygon();
        arrowhead.getPoints().addAll(-size, -size,
                0.0, 0.0,
                -size, size);
        handleChange();
    }

    Node create() {
        Group group = new Group();
        arrowhead.setFill(Color.STEELBLUE);
        group.getChildren().addAll(arrowhead);
        return group;
    }

    private void handleChange() {
        double angle = Math.toDegrees(Math.atan2(endY - startY, endX - startX));

        arrowhead.setTranslateY(endY + (startY - endY) / 2);
        arrowhead.setTranslateX(endX + (startX - endX) / 2);

        arrowhead.translateXProperty();
        arrowhead.getTransforms().add(Transform.rotate(angle, 0, 0));
    }

}