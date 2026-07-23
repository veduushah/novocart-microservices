import React, { useEffect, useState } from 'react';
import { orderApi } from '../utils/api';

interface Order {
  id: string;
  productName: string;
  price: number;
  quantity: number;
  total: number;
  orderedAt: string;
  status: 'placed' | 'shipped' | 'delivered' | 'cancelled';
}

export const OrdersTable: React.FC = () => {
  const [orders, setOrders] = useState<Order[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => { void fetchOrders(); }, []);

  const fetchOrders = async () => {
    setLoading(true);
    const response = await orderApi.get<Order[]>('/');
    if (response.success && response.data) setOrders(response.data);
    else setError(response.error || 'Unable to load your orders.');
    setLoading(false);
  };

  if (loading) return <div className="py-12 text-center text-gray-600">Loading your orders…</div>;
  if (error) return <div className="card p-8 text-center"><p className="mb-4 text-red-700">{error}</p><button onClick={fetchOrders} className="btn btn-primary btn-md">Try Again</button></div>;
  if (orders.length === 0) return <div className="card p-12 text-center"><h3 className="mb-2 text-xl font-semibold">No orders yet</h3><p className="text-gray-600">Find something you love in the NovaCart catalog.</p></div>;

  return (
    <div className="space-y-4">
      {orders.map((order) => (
        <article key={order.id} className="card flex flex-col gap-4 p-6 sm:flex-row sm:items-center sm:justify-between">
          <div>
            <h3 className="text-lg font-semibold text-gray-900">{order.productName}</h3>
            <p className="mt-1 text-sm text-gray-600">Ordered {new Date(order.orderedAt).toLocaleDateString()} · Qty {order.quantity}</p>
          </div>
          <div className="flex items-center gap-4">
            <span className="badge badge-primary">{order.status}</span>
            <span className="font-bold text-gray-900">${order.total.toFixed(2)}</span>
          </div>
        </article>
      ))}
    </div>
  );
};
