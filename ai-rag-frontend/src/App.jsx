
import { BrowserRouter, Routes, Route, Outlet } from "react-router-dom";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";

function Layout() {
  return (
    <div className="min-h-screen bg-slate-50">
      <Navbar />

      <div className="flex">
        <Sidebar />

        <main className="flex-1 p-6">
          <Outlet />
        </main>
      </div>
    </div>
  );
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout />}>
          <Route path="/" element={<h1>Chat Page</h1>} />
          <Route path="/add-text" element={<h1>Add Text Page</h1>} />
          <Route path="/upload-document" element={<h1>Upload Document Page</h1>} />
          <Route path="/documents" element={<h1>Documents Page</h1>} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
