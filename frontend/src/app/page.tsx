import { CardTemplate } from '@/components/templates/CardTemplate';
import { Suspense } from 'react';

// Datos de prueba (se reemplazarán por API)
const mockTemplates = [
  {
    id: 1,
    title: "Playa Paradisíaca",
    description: "Paquete todo incluido",
    price: 1200,
    //image: "/beach.jpg"
  },
  {
    id: 2,
    title: "Aventura Montaña",
    description: "Tour guiado",
    price: 800,
    //image: "/mountain.jpg"
  }
];

export default function Home() {
  return (
    <main className="container mx-auto p-4">
      <h1 className="text-3xl font-bold my-8">Mis Plantillas</h1>
      
      <Suspense fallback={<div>Cargando...</div>}>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {mockTemplates.map((template) => (
            <CardTemplate key={template.id} data={template} />
          ))}
        </div>
      </Suspense>
    </main>
  );
}