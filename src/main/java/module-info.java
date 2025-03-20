module edu.ntnu.idatt2003.a5cardgame {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.bjorav.ovinger to javafx.fxml;
  exports com.bjorav.ovinger;
}