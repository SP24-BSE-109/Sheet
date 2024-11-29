package com.sheet;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationController{
    public static void FadeOutAnimate(Node node){
        FadeTransition fade = new FadeTransition(Duration.seconds(2), node);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.play();
    }
    public static void FadeInAnimate(Node node){
        FadeTransition fade = new FadeTransition(Duration.seconds(2), node);
        fade.setFromValue(0.0);
        fade.setToValue(1);
        fade.play();
    }
}