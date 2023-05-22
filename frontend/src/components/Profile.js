import React from "react";
import { Navigate, Link } from "react-router-dom";
import { useSelector } from "react-redux";
import UserApi from "../services/user.service";
import { useState } from "react";
import "./profile.css";
const Profile = () => {
  const { user: currentUser } = useSelector((state) => state.auth);
  // const [orderList, setOrderList] = useState([]);
  const [orderProductList, setOrderProductList] = useState([{}]);
  const [totalOrder, setOrderTotal] = useState(0);
  if (!currentUser) {
    return <Navigate to="/login" />;
  }
  const items = [];
  const total = [];
  const key = currentUser.id;
  const getMyOrder = () => {
    UserApi.get(key)
      .then((response) => {
        // console.log(response);
        response.carts.map((order, index) => {
          // console.log("Arr " + (index + 1), order.products);
          items.push(...order.products);
          total.push(order.amount);
        });
        setOrderProductList(items);
        let sum = 0;
        for (let i = 0; i < total.length; i++) {
          Number((sum += total[i]));
        }
        setOrderTotal(sum);
      })
      .catch((e) => console.log(e));
  };
  console.log(orderProductList);
  return (
    <>
      <div className="row">
        <div className="col-6">
          <div className="container " key={currentUser.id}>
            <header className="jumbotron">
              <h3>
                <strong>{currentUser.username}</strong> Profile
              </h3>
            </header>

            <p>
              <strong>
                Token:{currentUser.token.substring(0, 20)} ...{" "}
                {currentUser.token.substr(currentUser.token.length - 20)}
              </strong>
            </p>

            <p>
              <strong>Id:</strong> {currentUser.id}
            </p>

            <p>
              <strong>Email:</strong> {currentUser.email}
            </p>

            <p>
              <strong>Phone:</strong> {currentUser.phone}
            </p>

            <p>
              <strong>Address:</strong> {currentUser.adress}
            </p>

            <strong>Authorities:</strong>
            <ul>
              {currentUser.roles &&
                currentUser.roles.map((role, index) => (
                  <li key={index}>{role}</li>
                ))}
            </ul>
            <button className="profile-btn">
              <Link to={"/editAccount"}>Edit</Link>
            </button>
            <button onClick={getMyOrder}>My Order</button>
          </div>
        </div>
        <div className="col-6">
          <div>
            <h2>
              {" "}
              Total{" "}
              {totalOrder.toLocaleString("vn-VN", {
                style: "currency",
                currency: "VND",
              })}
            </h2>
            {orderProductList.map((item) => {
              return (
                <div className="oder-item">
                  <img src={item.img} className="oder-item-img"></img>
                  <p className="order-item-name">{item.name}</p>
                  <p></p>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </>
  );
};

export default Profile;
