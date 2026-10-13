
import { NavLink } from "react-router-dom";
import {
  MessageCircle,
  FilePlus,
  Upload,
  Files
} from "lucide-react";

const menuItems = [
  { name: "Chat", path: "/", icon: MessageCircle },
  { name: "Add Text", path: "/add-text", icon: FilePlus },
  { name: "Upload Document", path: "/upload-document", icon: Upload },
  { name: "Documents", path: "/documents", icon: Files }
];

function Sidebar() {
  return (
    <aside className="w-64 min-h-[calc(100vh-80px)] bg-white border-r border-slate-200 p-4 shrink-0">
      <nav className="space-y-3">
        {menuItems.map(({ name, path, icon: Icon }) => (
          <NavLink
            key={path}
            to={path}
            end={path === "/"}
            className={({ isActive }) =>
              `flex items-center gap-3 px-4 py-3 rounded-lg transition-colors ${
                isActive
                  ? "bg-blue-600 text-white"
                  : "text-slate-700 hover:bg-blue-50"
              }`
            }
          >
            <Icon size={22} />
            <span>{name}</span>
          </NavLink>
        ))}
      </nav>
    </aside>
  );
}

export default Sidebar;
