const apiUrl = 'http://localhost:8080/pmsapp/webapi/products';

async function getProductById(id) {
  const response = await fetch(`${apiUrl}/view?id=${id}`);
  if (!response.ok) throw new Error('Failed to fetch product');
  return response.json();
}

async function updateProduct(id, product) {
  const response = await fetch(`${apiUrl}/edit/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(product),
  });
  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData || 'Failed to update product');
  }
  return response.json();
}

// Populate form on page load
document.addEventListener('DOMContentLoaded', async () => {
  const urlParams = new URLSearchParams(window.location.search);
  const productId = urlParams.get('id');

  if (!productId) {
    alert('No product ID provided');
    window.location.href = 'index.html';
    return;
  }

  try {
    const product = await getProductById(productId);

    document.getElementById('productId').value = product.productId;
    document.getElementById('productName').value = product.productName;
    document.getElementById('productDescription').value = product.productDescription;
    document.getElementById('productPrice').value = product.productPrice;
    document.getElementById('productReleasedOn').value = product.productReleasedOn;
  } catch (error) {
    alert('Error loading product: ' + error.message);
    console.error(error);
    window.location.href = 'index.html';
  }
});

// Handle form submit for update
document.getElementById('editForm').addEventListener('submit', async (event) => {
  event.preventDefault();

  const id = parseInt(document.getElementById('productId').value);
  const productName = document.getElementById('productName').value.trim();
  const productDescription = document.getElementById('productDescription').value.trim();
  const productPrice = parseFloat(document.getElementById('productPrice').value);
  const productReleasedOn = document.getElementById('productReleasedOn').value;

  if (!id || !productName || isNaN(productPrice) || !productReleasedOn) {
    alert('Please fill all required fields correctly.');
    return;
  }

  const updatedProduct = {
    productId: id,
    productName,
    productDescription,
    productPrice,
    productReleasedOn,
  };

  try {
    await updateProduct(id, updatedProduct);
    alert('Product updated successfully!');
    window.location.href = 'index.html'; // redirect to list page
  } catch (error) {
    alert('Error updating product: ' + error.message);
    console.error(error);
  }
});
