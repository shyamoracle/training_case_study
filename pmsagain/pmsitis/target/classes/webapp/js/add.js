const apiUrl = 'http://localhost:8080/pmsapp/webapi/products/add';

async function addProduct(product) {
  const response = await fetch(apiUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(product)
  });

  if (!response.ok) {
    // Try to parse error message if possible
    const errorData = await response.json().catch(() => null);
    const errorMsg = errorData?.message || 'Failed to add product';
    throw new Error(errorMsg);
  }

  // Parse and return response body if needed, or just await to avoid unused variable
  await response.json();
}

document.getElementById('addForm').addEventListener('submit', async function(event) {
  event.preventDefault();

  const productId = parseInt(document.getElementById('productId').value);
  const productName = document.getElementById('productName').value.trim();
  const productDescription = document.getElementById('productDescription').value.trim();
  const productPrice = parseFloat(document.getElementById('productPrice').value);
  const productReleasedOn = document.getElementById('productReleasedOn').value;

  if (!productId || productId <= 0) {
    alert('Product ID should be a positive number.');
    return;
  }

  if (!productName) {
    alert('Product Name is required.');
    return;
  }

  if (isNaN(productPrice) || productPrice <= 0) {
    alert('Product Price should be a positive number.');
    return;
  }

  if (!productReleasedOn) {
    alert('Please select a Release Date.');
    return;
  }

  const newProduct = {
    productId,
    productName,
    productDescription,
    productPrice,
    productReleasedOn
  };

  try {
    await addProduct(newProduct);
    alert('Product added successfully!');
    document.getElementById('addForm').reset();
    window.location.href = 'index.html'; // Redirect to your main page
  } catch (error) {
    alert('Error adding product: ' + error.message);
    console.error(error);
  }
});
