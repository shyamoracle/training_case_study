document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("loginForm");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const username = document.getElementById("dbUsername").value.trim();
    const password = document.getElementById("dbPassword").value.trim();

    const payload = { username, password };

    try {
      const res = await fetch("/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      if (res.ok) {
        alert("✅ Login successful! DB credentials updated.");
        // Optionally refresh data with new DB creds
        window.location.reload();
      } else {
        const errText = await res.text();
        alert("❌ Login failed: " + errText);
      }
    } catch (err) {
      console.error("Login error:", err);
      alert("❌ Network error while logging in.");
    }
  });
});
