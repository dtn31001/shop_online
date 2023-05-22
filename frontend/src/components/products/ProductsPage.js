import "bootstrap/dist/css/bootstrap.css";
import "./ProductCard.css";
import ProductCard from "./ProductCard";
const ListProduct = (props) => {
  const { productList } = props;

  return (
    <>
      <section className="product-container">
        <div className="product-row">
          {productList.map((product) => {
            return (
              <div key={product.id}>
                <ProductCard
                  id={product.id}
                  name={product.name}
                  price={product.price}
                  discount={product.discount}
                  catalogName={product.catalogName}
                  description={product.description}
                  img={product.img}
                />
              </div>
            );
          })}
        </div>
      </section>
    </>
  );
};
export default ListProduct;
