// sort.js

let currentSort = { column: 'productId', ascending: true };

function initSortUI(products, onSortedCallback) {
  const sortSelect = document.getElementById("sortSelect");
  const toggleOrderBtn = document.getElementById("toggleOrderBtn");

  // Set default text
  toggleOrderBtn.textContent = currentSort.ascending ? "Ascending" : "Descending";

  // Event: Change column
  sortSelect.addEventListener("change", () => {
    currentSort.column = sortSelect.value;
    const sorted = sortProducts(products);
    onSortedCallback(sorted);
  });

  // Event: Toggle order
  toggleOrderBtn.addEventListener("click", () => {
    currentSort.ascending = !currentSort.ascending;
    toggleOrderBtn.textContent = currentSort.ascending ? "Ascending" : "Descending";
    const sorted = sortProducts(products);
    onSortedCallback(sorted);
  });

  // Initial sort
  return sortProducts(products);
}

function sortProducts(products) {
  return [...products].sort((a, b) => {
    let valA = a[currentSort.column];
    let valB = b[currentSort.column];

    if (!isNaN(valA) && !isNaN(valB)) {
      valA = parseFloat(valA);
      valB = parseFloat(valB);
    } else if (isValidDate(valA) && isValidDate(valB)) {
      valA = new Date(valA);
      valB = new Date(valB);
    } else {
      valA = valA.toString().toLowerCase();
      valB = valB.toString().toLowerCase();
    }

    if (valA < valB) return currentSort.ascending ? -1 : 1;
    if (valA > valB) return currentSort.ascending ? 1 : -1;
    return 0;
  });
}

function isValidDate(val) {
  return !isNaN(Date.parse(val));
}
