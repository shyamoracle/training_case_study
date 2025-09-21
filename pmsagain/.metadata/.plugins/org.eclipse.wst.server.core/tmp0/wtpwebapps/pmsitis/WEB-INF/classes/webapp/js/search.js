// search.js
document.addEventListener('DOMContentLoaded', () => {
  const searchInput = document.getElementById('searchInput');
  const tableBody = document.getElementById('productTableBody');

  if (!searchInput || !tableBody) return; // safety check

  searchInput.addEventListener('input', () => {
    const filter = searchInput.value.toLowerCase();
    const rows = tableBody.getElementsByTagName('tr');

    for (let row of rows) {
      let text = '';
      for (let cell of row.cells) {
        text += cell.textContent.toLowerCase() + ' ';
      }
      row.style.display = text.includes(filter) ? '' : 'none';
    }
  });
});
/**
 * 
 */