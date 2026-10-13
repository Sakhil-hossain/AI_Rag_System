
import { useState } from "react";
import { BookOpen, Sun, Moon } from "lucide-react";

function Navbar() {
  const [darkMode, setDarkMode] = useState(false);

  const toggleTheme = () => {
    const nextMode = !darkMode;
    setDarkMode(nextMode);
    document.documentElement.classList.toggle("dark", nextMode);
  };

  return (
    <nav className="w-full h-16 bg-white dark:bg-slate-900 border-b border-gray-200 dark:border-slate-700 flex items-center justify-between px-6">

      <div className="flex items-center gap-3">
        <div className="bg-blue-600 p-2 rounded-lg">
          <BookOpen size={22} className="text-white" />
        </div>

        <h1 className="text-xl font-semibold text-slate-900 dark:text-white">
          AI RAG System
        </h1>
      </div>

      <button
        type="button"
        onClick={toggleTheme}
        className="p-2 rounded-lg hover:bg-gray-100 dark:hover:bg-slate-800 transition cursor-pointer"
        aria-label="Toggle dark mode"
      >
        {darkMode ? (
          <Sun size={22} className="text-yellow-400" />
        ) : (
          <Moon size={22} className="text-slate-600" />
        )}
      </button>

    </nav>
  );
}

export default Navbar;
