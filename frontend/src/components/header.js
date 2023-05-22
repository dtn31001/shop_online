import "bootstrap/dist/css/bootstrap.css";

import React from "react";
import "../components/header.css";
import Profile from "./Profile";
import Login from "./Login";
import AddProduct from "./products/add-product.component";
import ListProductByCategories from "./products/ProductByCategory";
import ProductApi from "../services/product.service";
import CategoryApi from "../services/catalog.service";
import ProductCard from "./products/ProductCard";

import ListProduct from "./products/ProductsPage";
import Home from "./home/Home";

import Register from "./Register";
import {
  BrowserRouter,
  Link,
  Route,
  Routes,
  useParams,
} from "react-router-dom";
import { login, logout } from "../actions/auth";
import { useState, useEffect, useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import EventBus from "../common/EventBus";
import { history } from "../helpers/history";
import { clearMessage } from "../actions/message";
import "./header.css";

import CartList from "./carts/cart-list";
import { CartContext } from "../context/CartContext";
import EditProduct from "./products/EditProduct";
import EditUser from "./EditUser";

function Header() {
  const [products, setProducts] = useState([]);
  useEffect(() => {
    // const ListP = () => {
    ProductApi.getAll("")
      .then((response) => setProducts(response.content))
      .catch((error) => console.log(error));
    // };
  }, []);

  const { user: currentUser } = useSelector((state) => state.auth);
  const [productList, setProductList] = useState([]);
  const dispatch = useDispatch();
  const [myCart, addToCart] = useState([{}]);
  const [total, setTotal] = useState(0);
  const [search, setSearch] = useState("");
  const [productBySearch, setProductBySearch] = useState([]);
  const [categoryList, setCategoryList] = useState([]);
  const [productListByCategoryId, setProductListByCategoryId] = useState([]);
  const [ids, setIds] = useState([]);

  const [count, setCount] = useState();

  const [edit, setEdit] = useState([{}]);

  const logOut = useCallback(() => {
    dispatch(logout());
  }, [dispatch]);

  useEffect(() => {
    history.listen((location) => {
      dispatch(clearMessage()); // clear message when changing location
    });
  }, [dispatch]);

  useEffect(() => {
    EventBus.on("logout", () => {
      logOut();
    });

    return () => {
      EventBus.remove("logout");
    };
  }, [currentUser, logOut]);

  const onChangeSearch = (e) => {
    setSearch(e.target.value);
  };

  const getProductBySearch = () => {
    const params = {
      search: search,
    };
    ProductApi.getAll(params)
      .then((response) => setProductList(response.content))
      .catch((error) => console.log(error));
  };
  useEffect(getProductBySearch, [
    currentUser,
    // productList,
    Login,
    EditProduct,
    ListProduct,
    AddProduct,
    ProductCard,
  ]);
  useEffect(() => {
    const getCategoriesList = async () => {
      const response = await CategoryApi.getAll();
      return response;
    };

    getCategoriesList()
      .then((response) => setCategoryList(response.data))
      .catch((error) => console.log(error));
  }, [ListProduct]);

  const getProductByCategoryId = async (key) => {
    await CategoryApi.get(key)
      .then((response) => setProductListByCategoryId(response.data.products))
      .catch((error) => console.log(error));
  };

  useEffect(getProductByCategoryId, [login]);

  return (
    <CartContext.Provider
      value={{
        myCart,
        addToCart,
        total,
        setTotal,
        productBySearch,
        setProductBySearch,
        edit,
        setEdit,
        ids,
        setIds,
        count,
        setCount,
        search,
        setSearch,
      }}
    >
      <BrowserRouter history={history}>
        <div>
          <div className="header">
            <nav className="navbar navbar-expand navbar-dark bg-dark">
              <div className="col-7 nav-item-left">
                <Link to={"/home"} className="navbar-brand">
                  VTIStore
                </Link>
                <div className="navbar-nav mr-auto">
                  {currentUser && (
                    <li className="nav-item">
                      <Link to={"/products"} className="nav-link">
                        Product List
                      </Link>
                    </li>
                  )}
                  {currentUser && (
                    <li className="nav-item">
                      <Link to={"/carts"} className="nav-link">
                        Cart List
                      </Link>
                      {{ myCart } ? (
                        <div className="cart-number-of">
                          <p className="cartnumberof">{myCart.length - 1}</p>
                        </div>
                      ) : (
                        ""
                      )}
                    </li>
                  )}
                </div>

                <div className="nav-item-log">
                  {currentUser ? (
                    currentUser.roles[0] === "ROLE_ADMIN" ||
                    currentUser.roles[0] === "ROLE_MANAGER" ? (
                      <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                          <Link to={"/add"} className="nav-link">
                            Add product
                          </Link>
                        </li>

                        <li className="nav-item">
                          <Link to={"/profile"} className="nav-link">
                            {currentUser.username}
                          </Link>
                        </li>

                        <li className="nav-item">
                          <a href="/home" className="nav-link" onClick={logOut}>
                            LogOut
                          </a>
                        </li>
                      </div>
                    ) : (
                      <div className="navbar-nav ml-auto">
                        <Link to={"/profile"} className="nav-link">
                          {currentUser.username}
                        </Link>
                        <li className="nav-item">
                          <a href="/home" className="nav-link" onClick={logOut}>
                            LogOut
                          </a>
                        </li>
                      </div>
                    )
                  ) : (
                    <div className="navbar-nav ml-auto">
                      <li className="nav-item">
                        <Link to={"/login"} className="nav-link">
                          Login
                        </Link>
                      </li>

                      <li className="nav-item">
                        <Link to={"/register"} className="nav-link">
                          Sign Up
                        </Link>
                      </li>
                    </div>
                  )}
                </div>
              </div>
              <div className="col-5">
                {currentUser && (
                  <div className="input-group mb-3  search-box">
                    <input
                      type="text"
                      className="form-control search-box-left"
                      placeholder="Search by Product Name"
                      // value={search}
                      onChange={onChangeSearch}
                    />

                    <div className="input-group-append btn-search">
                      <button
                        className="btn btn-outline-secondary"
                        type="button"
                        onClick={getProductBySearch}
                      >
                        Search
                      </button>
                    </div>
                  </div>
                )}
              </div>
            </nav>
            <div className="header-footer">
              <ul className="categories">
                {categoryList.map((category) => (
                  <li
                    className="category-item"
                    key={category.id}
                    onClick={() => getProductByCategoryId(category.id)}
                  >
                    <Link to={`Categories`} className="category-item">
                      {category.name}
                    </Link>
                  </li>
                ))}
              </ul>
            </div>
          </div>

          <div className="container mt-3">
            <Routes>
              <Route exact path="/login" element={<Login></Login>} />
              <Route exact path="/register" element={<Register></Register>} />
              <Route path="/profile" element={<Profile></Profile>} />
              <Route
                path="/products"
                element={<ListProduct productList={productList} />}
              />
              <Route
                path="/add"
                element={<AddProduct categoryList={categoryList} />}
              />
              <Route path="/carts" element={<CartList />} />
              {/* <Route path="/catalogs" element={"catalog List"} /> */}
              <Route path="/edit" element={<EditProduct />} />
              <Route path="/home" element={<Home />} />
              {/* <Route
                path="/home"
                element={<ListProduct productList={productList} />}
              /> */}
              <Route path="/editAccount" element={<EditUser />} />
              <Route
                path="/Categories"
                element={
                  <ListProductByCategories
                    productListByCategoryId={productListByCategoryId}
                  />
                }
              />
            </Routes>
          </div>
        </div>
      </BrowserRouter>
    </CartContext.Provider>
  );
}
export default Header;
