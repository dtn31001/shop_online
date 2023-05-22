import "bootstrap/dist/css/bootstrap.css";
import Form from "react-bootstrap/Form";
import "./add-product.css";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Modal, Button, Alert } from "react-bootstrap";
import ProductApi from "../../services/product.service";

const AddProduct = (props) => {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [discount, setDiscount] = useState("");
  const [catalogName, setCatalogName] = useState("");
  const [description, setDescription] = useState("");
  const [img, setImg] = useState("");
  const [saveProduct, setSaveProduct] = useState("");
  const [show, setShow] = useState(false);
  const handleClose = () => {
    setShow(false);
  };
  const { categoryList } = props;

  const handleProductNameChange = (e) => {
    const name = e.target.value;
    setName(name);
    console.log(typeof name);
  };

  const handlePriceChange = (e) => {
    const price = e.target.value;
    setPrice(price);
  };
  const handleDiscountChange = (e) => {
    const discount = e.target.value;
    setDiscount(discount);
  };
  const handleCatagoriesChange = (e) => {
    const catalogName = e.target.value;
    setCatalogName(catalogName);
    console.log(typeof catalogName);
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

    data.append("name", name);
    data.append("price", price);
    data.append("discount", discount);
    data.append("catalogName", catalogName);
    data.append("description", description);
    data.append("file", img);

    setSaveProduct(data);

    console.log("DATA", data);
  };

  const saveProducts = () => {
    ProductApi.create(saveProduct)
      .then((response) => setShow(true))
      .catch((error) => console.log(error));
  };
  useEffect(saveProducts, []);
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
            placeholder="Input Product Name..."
            onChange={handleProductNameChange}
          />
        </div>

        <div className="form-group col-6">
          <label htmlFor="productPrice"> Product Price</label>
          <input
            type="decimal"
            className="form-control"
            id="price"
            name="price"
            placeholder="Input Product Price"
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
            placeholder="Input Discount ...%"
            onChange={handleDiscountChange}
          />
        </div>
        <div className="form-group col-6">
          <label htmlFor="catalogName">Category Name</label>
          <Form.Select
            aria-label="Default select example"
            id="catalogName"
            name="catalogName"
            onChange={handleCatagoriesChange}
            type="text"
          >
            <option>Open this select Categories</option>
            {/* console.log(categoryList); */}
            {categoryList &&
              categoryList.map((category) => (
                <option value={category.name}>{category.name}</option>
              ))}
            ;
          </Form.Select>
        </div>

        <div className="form-group col-6">
          <label htmlFor="productDescription">Description</label>
          <textarea
            type="text"
            className="form-control"
            id="description"
            name="description"
            placeholder="Input Description"
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

        <button className="btn btn-success" onClick={saveProducts}>
          Add Product
        </button>
      </form>
      <div>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Add Product</Modal.Title>
          </Modal.Header>
          <Modal.Body>You added {name} successfully</Modal.Body>
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
      {/* <div>
        <Alert show="true">Updated Successfully</Alert>
      </div> */}
    </div>
  );
};

export default AddProduct;
