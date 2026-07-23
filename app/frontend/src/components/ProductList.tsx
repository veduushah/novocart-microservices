import React, { useEffect, useState } from 'react';
import { orderApi, productApi } from '../utils/api';

export interface Product {
  id: string;
  name: string;
  description: string;
  category: string;
  price: number;
  stockQuantity: number;
}

export const ProductList: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [ordering, setOrdering] = useState<string | null>(null);

  useEffect(() => { void fetchProducts(); }, []);

  const fetchProducts = async () => {
    setLoading(true);
    const response = await productApi.get<Product[]>('/');
    if (response.success && response.data) setProducts(response.data);
    else setError(response.error || 'Unable to load products.');
    setLoading(false);
  };

  const placeOrder = async (product: Product) => {
    setOrdering(product.id);
    const response = await orderApi.post('/', {
      productId: product.id,
      productName: product.name,
      price: product.price,
      quantity: 1,
    });
    setOrdering(null);
    if (response.success) alert(`${product.name} has been added to your orders.`);
    else alert(response.error || 'Unable to place your order.');
  };

  if (loading) return <div className="py-12 text-center text-gray-600">Loading products…</div>;

  if (error) {
    return <div className="card p-8 text-center"><p className="mb-4 text-red-700">{error}</p><button onClick={fetchProducts} className="btn btn-primary btn-md">Try Again</button></div>;
  }

  return (
    <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
      {products.map((product) => {
        const isOutOfStock = product.stockQuantity === 0;
        return (
          <article key={product.id} className="card-hover rounded-xl border bg-white p-6 shadow-sm">
            <div className="mb-4 flex items-start justify-between gap-4">
              <span className="rounded-full bg-primary-100 px-3 py-1 text-xs font-semibold text-primary-700">{product.category}</span>
              <span className="text-xl font-bold text-gray-900">${product.price.toFixed(2)}</span>
            </div>
            <h3 className="mb-2 text-xl font-bold text-gray-900">{product.name}</h3>
            <p className="mb-6 text-sm leading-relaxed text-gray-600">{product.description}</p>
            <div className="mb-5 text-sm text-gray-500">
              {isOutOfStock ? 'Out of stock' : `${product.stockQuantity} in stock`}
            </div>
            <button onClick={() => placeOrder(product)} disabled={isOutOfStock || ordering === product.id} className="btn btn-primary btn-lg w-full">
              {ordering === product.id ? 'Placing order…' : isOutOfStock ? 'Out of Stock' : 'Buy Now'}
            </button>
          </article>
        );
      })}
    </div>
  );
};
