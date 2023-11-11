package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {
    @FXML private TextField nombre;
    @FXML private TextField descripcion;
    @FXML private TextField precio;
    @FXML private TextField stock;
    @FXML private DatePicker fechaCreacion;
    @FXML private DatePicker fechaModificacion;
    @FXML private Button editProductoButton;
    @FXML private Button deleteProductoButton;
    @FXML private TableView<Producto> productosTable;
    @FXML private TableColumn<Producto, Integer> idColumn;
    @FXML private TableColumn<Producto, String> nombreColumn;
    @FXML private TableColumn<Producto, String> descripcionColumn;
    @FXML private TableColumn<Producto, Double> precioColumn;
    @FXML private TableColumn<Producto, String> categoriaColumn;

    private Producto selectedProduct = null;
    private Usuario loggedInUser = null;
    private DBConnection DB = new DBConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedInUser = AppData.getLoggedInUsername();
        setProductosTable();
        editProductoButton.setDisable(true);
        deleteProductoButton.setDisable(true);

        productosTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Producto>() {
            @Override
            public void changed(ObservableValue<? extends Producto> observable, Producto oldValue, Producto newValue) {
                if (newValue != null) {
                    selectedProduct = newValue;
                    editProductoButton.setDisable(false);
                    deleteProductoButton.setDisable(false);
                } else {
                    editProductoButton.setDisable(true);
                    deleteProductoButton.setDisable(true);
                }
            }
        });
    }

    private void unselectedProductAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Producto no seleccionado");
        alert.setHeaderText("No se ha seleccionado ningún producto");
        alert.setContentText("Por favor, selecciona un producto de la lista para cargar sus detalles.");
        alert.showAndWait();
    }


    public void setProductosTable() {
        productosTable.setMinWidth(530);
        // Configuramos las columnas para que correspondan a una propiedad de la clase Producto
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
//        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        // TAMAÑOS DE LAS COLUMNAS
        idColumn.setMinWidth(10);
        nombreColumn.setMinWidth(100);
//        descripcionColumn.setMinWidth(200);
        precioColumn.setMinWidth(100);
//        categoriaColumn.setMinWidth(100);

        try {
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM PRODUCTO";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
                while (result.next()) {
                    Producto producto = new Producto(
                            result.getInt("id_producto"),
                            result.getString("nombre"),
                            result.getString("descripcion"),
                            result.getDouble("precio"),
                            null
                    );
                    listaProductos.add(producto);
                }
                productosTable.setItems(listaProductos);
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de cargar los productos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private void setProductoForm() {
        if (selectedProduct == null) {
            unselectedProductAlert();
            return;
        }
        nombre.setText(selectedProduct.getNombre());
        descripcion.setText(selectedProduct.getDescripcion());
        precio.setText(String.valueOf(selectedProduct.getPrecio()));
        stock.setText(String.valueOf(selectedProduct.getStock()));
    }

    @FXML
    private void deleteProducto() {
        if (selectedProduct == null) {
            unselectedProductAlert();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText("¿Deseas eliminar el producto " + selectedProduct.getNombre() + "?");
        alert.setContentText("Seleccione una opción:");
        ButtonType buttonTypeSi = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);

        // Mostrar la alerta y esperar a que el usuario seleccione una opción
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeSi) {
                // El usuario seleccionó "Sí"
                try {
                    Connection connection = DB.getConnection();
                    String query = "DELETE FROM PRODUCTO WHERE id_producto = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, selectedProduct.getIdProducto());
                    statement.executeUpdate();
                    setProductosTable();
                } catch (SQLException e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Error al tratar de eliminar el producto");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        });
    }

    @FXML
    private void handleSubmit() {
        if (!isAllFieldsValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor rellene todos los campos correctamente");
            alert.showAndWait();
            return;
        }

        String nombreValue = nombre.getText();
        String descripcionValue = descripcion.getText();
        double precioValue = Double.parseDouble(precio.getText());
        int stockValue = Integer.parseInt(stock.getText());

        java.sql.Date fechaCreacionValue = null;
        if (fechaCreacion.getValue() != null) {
            fechaCreacionValue = java.sql.Date.valueOf(fechaCreacion.getValue());
        }

        java.sql.Date fechaModificacionValue = java.sql.Date.valueOf(java.time.LocalDate.now());

        try {
            Connection connection = DB.getConnection();
            String query;
            if (selectedProduct == null) {
                // Insertar nuevo producto
                query = "INSERT INTO PRODUCTO (nombre, descripcion, precio, stock, id_categoria, fecha_creacion, fecha_modificacion, usuario_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                // Actualizar producto existente
                query = "UPDATE PRODUCTO SET nombre = ?, descripcion = ?, precio = ?, stock = ?, id_categoria = ?, fecha_modificacion = ?, usuario_mod = ? WHERE id_producto = ?";
            }

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombreValue);
            statement.setString(2, descripcionValue);
            statement.setDouble(3, precioValue);
            statement.setInt(4, stockValue);
            statement.setDate(5, fechaModificacionValue);
            statement.setInt(6, loggedInUser.getIdUsuario());

            if (selectedProduct != null) {
                statement.setInt(7, selectedProduct.getIdProducto());
            } else {
                statement.setDate(5, fechaCreacionValue);
                statement.setInt(6, loggedInUser.getIdUsuario());
            }

            statement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("Producto guardado correctamente");
            alert.showAndWait();

            clearAllFields();
            setProductosTable();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de guardar el producto");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private void clearAllFields() {
        nombre.clear();
        descripcion.clear();
        precio.clear();
        stock.clear();
        fechaCreacion.setValue(null);
        fechaModificacion.setValue(null);
    }
    private boolean isAllFieldsValid() {
        String errorMessage = "";

        if (nombre.getText() == null || nombre.getText().length() == 0) {
            errorMessage += "Nombre no válido.\n";
        }
        if (descripcion.getText() == null || descripcion.getText().length() == 0) {
            errorMessage += "Descripción no válida.\n";
        }
        if (precio.getText() == null || precio.getText().length() == 0) {
            errorMessage += "Precio no válido.\n";
        } else {
            // Intenta convertir el texto del precio a un número
            try {
                Double.parseDouble(precio.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Precio debe ser un número.\n";
            }
        }
        if (stock.getText() == null || stock.getText().length() == 0) {
            errorMessage += "Stock no válido.\n";
        } else {
            // Intenta convertir el texto del stock a un número entero
            try {
                Integer.parseInt(stock.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Stock debe ser un número entero.\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Muestra el mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos inválidos");
            alert.setHeaderText("Por favor, corrige los campos inválidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

}
