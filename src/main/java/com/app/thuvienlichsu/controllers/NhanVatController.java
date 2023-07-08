package com.app.thuvienlichsu.controllers;

import com.app.thuvienlichsu.base.Model;
import com.app.thuvienlichsu.base.NhanVatModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NhanVatController extends GeneralController implements Initializable {
    public FlowPane cacNhanVatLienQuan;
    public FlowPane cacDiTichLienQuan;
    public FlowPane cacThoiKyLienQuan;
    public Label diTichLienQuanLabel;
    public Label nhanVatLienQuanLabel;
    public Label thoiKyLienQuanLabel;
    public VBox tableContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getNhanVat()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);

    }
    public void preloadNhanVat(String nhanVatName) {
        listView.getSelectionModel().select(nhanVatName);
        showNhanVatDetail(nhanVatName);

    }
    @FXML
    public void nhanVatSearchFieldAction() {
        searchFieldAction((ArrayList<Model>) database.getNhanVat());
    }
    @FXML
    public void showNhanVatDetail() {
        NhanVatModel nhanVat = (NhanVatModel) getModelFromDatabase((ArrayList<Model>) database.getNhanVat());
        showNhanVatInformation(nhanVat);
        showDanhSachLienQuan(nhanVat);
    }
    public void showNhanVatDetail(String nhanVatName) {
        NhanVatModel nhanVat = (NhanVatModel) getModelFromDatabase((ArrayList<Model>) database.getNhanVat(), nhanVatName);
        showNhanVatInformation(nhanVat);
        showDanhSachLienQuan(nhanVat);
    }
    private void showNhanVatInformation(NhanVatModel nhanVat){
        tableContent.getChildren().clear();
        Label label = new Label(nhanVat.getTenModel());
        label.setPadding(new Insets(0, 0, 0, 10));
        label.setFont(new Font(20));
        tableContent.getChildren().add(label);
        GridPane infoTable = nhanVat.getInfoTable();
        if (infoTable != null) {
            tableContent.getChildren().add(infoTable);
        }

        TextFlow description = nhanVat.getDescription();
        if (description != null)
            tableContent.getChildren().add(description);
    }
    private void showDanhSachLienQuan(NhanVatModel nhanVat){
        resetDanhSachLienQuan();

        if (nhanVat == null) return;

        if (nhanVat.getCacNhanVatLienQuan().size() > 0) nhanVatLienQuanLabel.setVisible(true);
        cacNhanVatLienQuan.getChildren().addAll(GeneralController.nhanVatLienQuanButtons(nhanVat.getCacNhanVatLienQuan(), database.getNhanVat()));

        if (nhanVat.getCacDiTichLienQuan().size() > 0) diTichLienQuanLabel.setVisible(true);
        cacDiTichLienQuan.getChildren().addAll(GeneralController.diTichLienQuanButtons(nhanVat.getCacDiTichLienQuan(), database.getDiTich()));

        if (nhanVat.getCacThoiKyLienQuan().size() > 0) thoiKyLienQuanLabel.setVisible(true);
        cacThoiKyLienQuan.getChildren().addAll(GeneralController.thoiKyLienQuanButtons(nhanVat.getCacThoiKyLienQuan(), database.getThoiKy()));
    }
    private void resetDanhSachLienQuan(){
        nhanVatLienQuanLabel.setVisible(false);
        diTichLienQuanLabel.setVisible(false);
        thoiKyLienQuanLabel.setVisible(false);

        cacNhanVatLienQuan.getChildren().clear();
        cacDiTichLienQuan.getChildren().clear();
        cacThoiKyLienQuan.getChildren().clear();
    }
}
