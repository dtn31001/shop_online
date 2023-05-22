import "bootstrap/dist/css/bootstrap.css";
import "./ProductCard.css";
import ProductCard from "./ProductCard";

const ListProductByCategories = (props) => {
  const { productListByCategoryId } = props;

  // console.log(productListByCategoryId);
  return (
    <>
      <section className="product-container">
        <div className="product-row">
          {productListByCategoryId.map((product) => {
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
export default ListProductByCategories;
