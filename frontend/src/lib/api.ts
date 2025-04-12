// Versión mockeada (se reemplazará luego)
export async function fetchTemplates() {
  // En el futuro: const res = await fetch('/api/templates');
  return Promise.resolve([
    {
      id: 1,
      title: "Playa Paradisíaca",
      description: "Paquete todo incluido",
      price: 1200
    }
  ]);
}