/** @format */

import { useContext, useState } from "react";
import { CartContext } from "../../context/CartContext";
import { useSelector } from "react-redux";
import ProductApi from "../../services/product.service";
import { Modal, Button } from "react-bootstrap";
import { Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";

import DeleteProduct from "./EditProduct";

import "./ProductCard.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPenToSquare, faTrashCan } from "@fortawesome/free-solid-svg-icons";

const ProductCard = (props) => {
  const { id, name, price, discount, catalogName, description, img } = props;
  const { user: currentUser } = useSelector((state) => state.auth);

  const [isAdded, setAdded] = useState(false);
  const { addToCart, setTotal, setEdit, setIds, setCount } = useContext(CartContext);
  const [quantity, setQuantity] = useState(1);

  const [show, setShow] = useState(false);
  // const [showEdit, setShowEdit] = useState(false);
  const handleClose = () => {
    setShow(false);
  };
  const handleDelete = () => {
    const item = {
      id: id,
    };
    console.log(id);
    ProductApi.delete(id)
      .then((response) => console.log("Delete Successful"))
      .catch((error) => console.log(error));
    setShow(false);
  };
  const handleShow = () => setShow(true);

  const handleShowEdit = () => {
    const item = {
      id: id,
      name: name,
      price: price,
      discount: discount,
      catalogName: catalogName,
      description: description,
      img: img,
    };
    setEdit(item);
    console.log(item);
  };

  const handleChangeQuantity = (e) => {
    setQuantity(e.target.value);
  };
  const handleClick = () => {
    setAdded(true);

    const newItems = {
      id: id,
      name: name,
      price: price,
      discount: discount,
      catalogName: catalogName,
      description: description,
      img: img,
      quantity: quantity,
    };

    setIds((item) => [...item, newItems.id]);

    // for
    addToCart((item) => [...item, newItems]);

    setTotal((total) => Number((total += price * (1 - discount / 100) * quantity)));
  };

  return (
    <>
      <section>
        <div className='product-item'>
          <img className='product-img' src={img} alt='image'></img>
          <div className='product-info'>
            <h4 className='product-info-name'>{name}</h4>
            <p className='product-info-description'>{description}</p>
            {/* {typeof discount} */}
            {!!discount ? (
              <div className='product-info-discount'>{discount}%OFF</div>
            ) : (
              <div type='hidden' className='product-info-discount-disable'>
                {discount}
              </div>
            )}
            <div className='product-info-price'>
              {!!discount ? (
                <p className='old'>
                  {price.toLocaleString("vn-VN", {
                    style: "currency",
                    currency: "VND",
                  })}
                </p>
              ) : (
                <p className='old-hidden'>{price}</p>
              )}

              <p className='new'>
                {(price * (1 - discount / 100)).toLocaleString("vn-VN", {
                  style: "currency",
                  currency: "VND",
                })}
              </p>
            </div>
          </div>

          {isAdded ? (
            <button className='product-btn-disabled' disabled>
              ADDED
            </button>
          ) : (
            <div className='product-footer'>
              <input type='number' min='1' className='product-quantity' placeholder='1' onChange={handleChangeQuantity} />

              <button className='product-btn' onClick={handleClick}>
                ADD TO CART
              </button>
            </div>
          )}
          <div>
            {/* <EditDelete /> */}
            {(currentUser.roles[0] === "ROLE_ADMIN" || currentUser.roles[0] === "ROLE_MANAGER") && (
              <button className='product-info-item-edit '>
                <Link to={"/edit"}>
                  <FontAwesomeIcon icon={faPenToSquare} className='edit' data-bs-toggle='modal' data-bs-target='#exampleModal' onClick={handleShowEdit} />
                </Link>

                <FontAwesomeIcon icon={faTrashCan} className='delete' onClick={handleShow} />
              </button>
            )}
            <div>
              <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                  <Modal.Title>Delete Product</Modal.Title>
                </Modal.Header>
                <Modal.Body>Do you wanna delete {name} ?</Modal.Body>
                <Modal.Footer>
                  <Button variant='secondary' onClick={handleClose}>
                    Close
                  </Button>
                  <Button variant='primary' onClick={(handleClose, handleDelete)}>
                    Delete
                  </Button>
                </Modal.Footer>
              </Modal>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default ProductCard;
