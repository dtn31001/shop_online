import "bootstrap/dist/css/bootstrap.css";
import Form from "react-bootstrap/Form";
import "./add-product.css";
import { useState } from "react";
import { Link } from "react-router-dom";
import { Modal, Button } from "react-bootstrap";
import ProductApi from "../../services/product.service";
import { CartContext } from "../../context/CartContext";
import { useContext } from "react";

const EditProduct = () => {
  const { edit } = useContext(CartContext);
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [discount, setDiscount] = useState("");
  const [description, setDescription] = useState("");
  const [img, setImg] = useState("");
  const [saveProduct, setSaveProduct] = useState("");
  const [show, setShow] = useState(false);
  const handleClose = () => {
    setShow(false);
  };
  const handlePriceChange = (e) => {
    const price = e.target.value;
    setPrice(price);
  };
  const handleDiscountChange = (e) => {
    const discount = e.target.value;
    setDiscount(discount);
  };

  const handleDescriptionChange = (e) => {
    const description = e.target.value;
    setDescription(description);
  };

  const handlePImageChange = (e) => {
    console.log(e.target.files);
    setImg(e.target.files[0]);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    var FormData = require("form-data");
    var data = new FormData();

    // data.append("name", name);
    data.append("price", price);
    data.append("discount", discount);
    data.append("description", description);
    data.append("file", img);

    setSaveProduct(data);
    console.log("DATA", data);
  };

  const saveProducts = (key) => {
    console.log(key);

    ProductApi.update(key, saveProduct)
      .then((response) => setShow(true))
      .catch((error) => console.log(error));
  };

  return (
    <div className="submit-form" onSubmit={handleSubmit}>
      <form action="." encType="multipart/form-data">
        <div className="form-group col-6">
          <label htmlFor="productName">Product Name</label>
          <input
            type="text"
            className="form-control"
            id="name"
            name="name"
            value={edit.name}
          />
        </div>

        <div className="form-group col-6">
          <label htmlFor="productPrice"> Product Price</label>
          <input
            type="decimal"
            className="form-control"
            id="price"
            name="price"
            placeholder={edit.price}
            onChange={handlePriceChange}
          />
        </div>

        <div className="form-group col-6">
          <label htmlFor="productDiscount"> Discount</label>
          <input
            type="decimal"
            className="form-control"
            id="discount"
            name="discount"
            placeholder={edit.discount}
            onChange={handleDiscountChange}
          />
        </div>
        <div className="form-group col-6">
          <label htmlFor="catalogName">Category Name</label>
          <input
            type="decimal"
            className="form-control"
            id="discount"
            name="discount"
            value={edit.catalogName}
          />
        </div>

        <div className="form-group col-6">
          <label htmlFor="productDescription">Description</label>
          <textarea
            type="text"
            className="form-control"
            id="description"
            name="description"
            placeholder={edit.description}
            onChange={handleDescriptionChange}
            rows="3"
          />
        </div>

        <Form.Group
          controlId="img"
          className="mb-3 col-6"
          id="img"
          name="img"
          onChange={handlePImageChange}
        >
          <Form.Label>Product Image</Form.Label>
          <Form.Control type="file" multiple />
        </Form.Group>

        <div className="mb-3 col-6 edit-form-footer">
          <button className="btn btn-primary ">
            <Link to="/products" className="edit-form-footer-left">
              Back
            </Link>
          </button>
          <button
            className="btn btn-success edit-form-footer-right"
            onClick={() => saveProducts(edit.id)}
          >
            Save Product
          </button>
        </div>
      </form>
      <div>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Update Product</Modal.Title>
          </Modal.Header>
          <Modal.Body>You updated to {name} successfully</Modal.Body>
          <Modal.Footer>
            <Button
              variant="secondary"
              className="btn btn-secondary"
              onClick={handleClose}
            >
              <Link to="/products">Close</Link>
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </div>
  );
};

export default EditProduct;
