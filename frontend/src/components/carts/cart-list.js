import { CartContext } from "../../context/CartContext";
import { useContext, useState } from "react";
import "bootstrap/dist/css/bootstrap.css";
import { Form } from "react-bootstrap";
import { useSelector } from "react-redux";
import "./cart-list.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrashCan } from "@fortawesome/free-solid-svg-icons";
import { Modal, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import CartAPI from "../../services/cart.service";

const CartList = () => {
  const { user: currentUser } = useSelector((state) => state.auth);
  const { myCart, total, ids, addToCart, setTotal } = useContext(CartContext);

  const [comment, setComment] = useState("");
  const [newTotal, setNewTotal] = useState(total);
  const [newCart, setNewCart] = useState(myCart.slice(1));
  const [show, setShow] = useState(false);
  const [newIds, setNewIds] = useState(ids);
  const [pay, setPay] = useState("");
  const [isPaid, setIsPaid] = useState("");

  const handleClose = () => {
    setShow(false);
  };
  const handleShow = () => setShow(true);

  const handleDescriptionChange = (e) => {
    setComment(e.target.value);
  };
  const handleChangePay = (e) => {
    setPay(e.target.value);
    setIsPaid(e.target.value);
  };
  const deleteProductFromCart = (key) => {
    var deleteTotal =
      newCart[key].price *
      newCart[key].quantity *
      (1 - newCart[key].discount / 100);
    const arr1 = newCart.slice(0, key);
    const arr2 = newCart.slice(key + 1, newCart.length);
    setNewCart([...arr1, ...arr2]);
    addToCart(newCart);
    const arr1Ids = newIds.slice(0, key);
    const arr2Ids = newIds.slice(key + 1, newCart.length);
    const result = [...arr1Ids, ...arr2Ids];
    setNewIds(result);
    // let sum=0;
    // for (let i = 0; i < result.length; i++) {
    //   Number((sum += result[i].price)*result[i].quantity) * (1 - newCart[key].discount / 100);;
    // }
    setNewTotal(Number(newTotal - deleteTotal));
  };

  const handleDelete = () => {
    newCart.length = [];
    setNewCart(newCart);
    const arr = [{}];
    addToCart((item) => [...arr, ...newCart]);
    newIds.length = [];
    setNewIds(newIds);
    setNewTotal(0);
    setTotal(0);
    setShow(false);
  };
  const data = {
    productId: newIds,
    userId: currentUser.id,
    comment: comment,
    paid: Boolean(isPaid),
    amount: newTotal,
  };
  // console.log(data);
  const checkOut = () => {
    if (!data.userId || !data.amount) {
      alert("You cannot Check out when carts is empty");
    } else {
      CartAPI.create(data)
        .then((response) => {
          console.log("OK");
          handleDelete();
          setComment("");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };
  return (
    <>
      <div className="cart-container">
        <div>
          <h1 className="cart-header">CHECKOUT</h1>
          <div className="cart-body-top col-6">
            <h3 className="cart-body-top-total">
              Product Total: {newCart.length}
            </h3>
            <p className="cart-body-top-delete">
              <button
                className="cart-body-top-delete-button btn btn-danger"
                onClick={handleShow}
              >
                Delete All
              </button>
            </p>
          </div>
        </div>
        <div className="cart-body-row row">
          <div className="cart-body-col col-6">
            <div className="cart-items">
              {newCart.map((item, index) => {
                return (
                  <div className="cart-item" key={index}>
                    <div className="cart-item-left">
                      <div>
                        <img
                          src={item.img}
                          alt="null"
                          className="cart-item__img "
                        />
                      </div>
                    </div>

                    <div className="cart-item-info">
                      <h5 className="cart-item__name">{item.name}</h5>
                      <h4 className="cart-item__price">
                        <b>
                          {(
                            item.price *
                            (1 - item.discount / 100)
                          ).toLocaleString("vn-VN", {
                            style: "currency",
                            currency: "VND",
                          })}
                        </b>
                      </h4>
                      <div className="cart-item-quantity-delete">
                        {/* <div style="display: flex">
                          <button>-</button>
                          <input type="number" />
                          <button>+</button>
                        </div> */}

                        <h6>{item.quantity}</h6>
                        <button className="cart-item-button-delete">
                          <FontAwesomeIcon
                            icon={faTrashCan}
                            className="delete"
                            onClick={() => deleteProductFromCart(index)}
                          />
                        </button>
                      </div>
                    </div>
                  </div>
                );
              })}
              ;
            </div>
          </div>

          <div className="cart-user-info col-6">
            <div>
              <h2 type="currency">
                TOTAL:
                {newTotal.toLocaleString("vn-VN", {
                  style: "currency",
                  currency: "VND",
                })}
              </h2>
            </div>
            <div className="form-group col-6">
              <label htmlFor="comment">Comment</label>
              <textarea
                type="text"
                className="form-control"
                id="comment"
                name="comment"
                placeholder="Input Comment"
                onChange={handleDescriptionChange}
                rows="3"
              />
            </div>
            <div className="payment-type">
              <Form>
                <Form.Check
                  label="Payment by Credit Card"
                  name="pay"
                  type="radio"
                  value="1"
                  id="Credit"
                  onChange={handleChangePay}
                />
                <Form.Check
                  label="Payment in Cash"
                  name="pay"
                  value=""
                  type="radio"
                  id="Cash"
                  onChange={handleChangePay}
                  checked
                />
              </Form>
            </div>
            <div>
              <button onClick={checkOut}>Check out</button>
            </div>
          </div>
        </div>
        {/* {newCart.length > 1 ? ( */}
        <div>
          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Delete Product</Modal.Title>
            </Modal.Header>
            <Modal.Body>Do you wanna delete all Products in Cart ?</Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
              <Button variant="danger" onClick={(handleClose, handleDelete)}>
                <Link to="/carts">Delete</Link>
              </Button>
            </Modal.Footer>
          </Modal>
        </div>
        {/* // ) : ( // alert("Carts is empty, please put products on Carts") // )} */}
      </div>
    </>
  );
};

export default CartList;
