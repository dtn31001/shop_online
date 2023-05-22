import "bootstrap/dist/css/bootstrap.css";
import Form from "react-bootstrap/Form";

import { useState } from "react";
import { Link } from "react-router-dom";
import { Modal, Button } from "react-bootstrap";

import { useSelector } from "react-redux";

import UserApi from "./../services/user.service";
const EditUser = () => {
  const { user: currentUser } = useSelector((state) => state.auth);
  const [phone, setPhone] = useState("");
  const [adress, setAdress] = useState("");
  const [userEditData, setUserEditData] = useState("");
  //   const [show, setShow] = useState(false);
  //   const handleClose = () => {
  //     setShow(false);
  //   };

  const handlePhoneChange = (e) => {
    setPhone(e.target.value);
  };
  const handleAddressChange = (e) => {
    setAdress(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      phone: phone,
      adress: adress,
    };
    setUserEditData(data);
  };

  const saveUser = (key) => {
    UserApi.update(key, userEditData)
      .then(alert("Update User successfully"))
      .catch((error) => console.log(error));
  };

  return (
    <div className="submit-form" key={currentUser.id} onSubmit={handleSubmit}>
      <form>
        <div className="form-group col-6">
          <label htmlFor="name">Name</label>
          <input
            type="text"
            className="form-control"
            id="name"
            name="name"
            value={currentUser.username}
          />
        </div>

        <div className="form-group col-6">
          <label htmlFor="email"> Email</label>
          <input
            type="text"
            className="form-control"
            id="email"
            name="email"
            value={currentUser.email}
          />
        </div>

        <div className="form-group col-6">
          <label htmlFor="phone"> Phone</label>
          <input
            type="text"
            className="form-control"
            id="phone"
            name="phone"
            placeholder={"Enter your Phone number"}
            onChange={handlePhoneChange}
          />
        </div>
        <div className="form-group col-6">
          <label htmlFor="adress"> Address</label>
          <input
            type="text"
            className="form-control"
            id="adress"
            name="adress"
            placeholder={"Enter your Address"}
            onChange={handleAddressChange}
          />
        </div>

        <div className="mb-3 col-6 edit-form-footer">
          <button className="btn btn-primary ">
            <Link to="/products" className="edit-form-footer-left">
              Back
            </Link>
          </button>
          <button
            className="btn btn-success edit-form-footer-right"
            onClick={() => saveUser(currentUser.id)}
          >
            Save
          </button>
        </div>
      </form>
      {/* <div>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Update Account</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            You updated to {currentUser.username} successfully
          </Modal.Body>
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
      </div> */}
    </div>
  );
};

export default EditUser;
